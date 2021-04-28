#! /bin/sh
# Required deployment directory structure:
# .
# ├── bin
# │    └── boot.sh
# ├── config
# ├── lib
# └── logs
#
set -o nounset
set -o errexit

PROJECT_NAME=CodeDemo
MAIN_CLASS=com.bingoabin.test.MysqlUtilTest

BASE_DIR=$(cd $(dirname $0)/../;pwd)

JAVA_OPTIONS=(
"-Xms512M"
'-Xmx2048M'
)

printout() {
    echo "【${PROJECT_NAME}】$@"
}

get_alive_num() {
    echo $(jps -l | grep ${MAIN_CLASS}| wc -l)
}

kill_alives() {
    while read id;do
        printout "  Killing $id ..."
        kill ${id}
    done
}

kill_until_exceed_maximum() {
    for i in {0..2}; do
        if [[ $(get_alive_num) -gt 0 ]]; then
            if [[ ${i} -gt 0 ]];then
                sleep 3
            fi
            jps -l |grep ${MAIN_CLASS} |awk '{print $1}' |kill_alives
        else
            printout "Stop done."
            return 0
        fi
    done
    printout "Stop failed!!!"
    exit 1
}

stop() {
    if [[ $(get_alive_num) -gt 0 ]];then
        printout "Stopping ..."
        kill_until_exceed_maximum
    else
        printout "No alive found."
    fi
}

start() {
    if [[ $(get_alive_num) -gt 0 ]]; then
        printout "Already running."
        exit 1
    fi
    # to ensure log file in "logs" dir but "./"
    cd ${BASE_DIR} && mkdir -p logs
    CONF_DIR=${BASE_DIR}/config
    LIB_DIR=${BASE_DIR}/lib
    printout "Starting ..."
    java ${JAVA_OPTIONS[@]} \
        -cp ${BASE_DIR}:${CONF_DIR}:${LIB_DIR}/*: ${MAIN_CLASS} \
        >  logs/app.out \
        2> logs/app.err
    printout "Start done."
}

restart() {
    stop && start
}

status() {
    jps -l |grep ${MAIN_CLASS} |awk -v PROJECT_NAME="$PROJECT_NAME" '{print "【" PROJECT_NAME "】", $0}'
}

log() {
    local logFile=${BASE_DIR}/logs/app.log
    if [[ -f $logFile ]]; then
        tail -f -n 30 $logFile
    else
        echo "Not found log file: [$logFile]"
    fi
}

printUsage() {
    printout "Usage: ./boot.sh [l]og | [l]i[s]t | [k]ill | [r]estart"
    exit 1
}

# Main
if [[ $# -ne 1 ]];then
    printUsage
fi

case $1 in
    l) log;;
    ls) status;;
    k) stop;;
    r) restart;;
    *) printUsage;;
esac
