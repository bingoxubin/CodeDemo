package com.bingoabin.utils.ddl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bingoabin.utils.exception.SqlParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.parse.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ouzhenwei
 * @ClassName DDLParser
 * @Description
 * @createTime 2021-07-29 17:26:58
 */
public class DDLParser extends AbstractParser{
    private int currentIndex = 0;

    private char[] chars;

    private int arrLength;

    private String targetDDL;


    public DDLParser(String targetDDL) {
        this.targetDDL = wrapIfNeed(targetDDL);
        this.chars = this.targetDDL.toCharArray();
        this.arrLength = this.chars.length;
    }

    public ETLInfo parse() throws SqlParseException {

        ETLInfo etlInfo = new ETLInfo();

        HiveTable hiveTable = null;

        try {
            hiveTable = HiveUtil.getTable(targetDDL);
        }catch (ParseException e){
            throw new SqlParseException(e.getMessage());
        }

        if(null == hiveTable){
            throw new SqlParseException("未解析到ddl");
        }

        etlInfo.setTableComment(hiveTable.getComment());
        if(null == hiveTable.getColumns()){
            throw new SqlParseException("未解析到ddl中的列");
        }

        etlInfo.setColumns(new ArrayList<>());
        etlInfo.setPartitionKeys(new ArrayList<>());
        for(HiveColumn hiveColumn : hiveTable.getColumns()){
            if(StringUtils.isBlank(hiveColumn.getName()) || StringUtils.isBlank(hiveColumn.getType())){
                continue;
            }
            ETLColumn column = new ETLColumn();
            column.setName(hiveColumn.getName().replace("`","").trim());
            column.setType(hiveColumn.getType().trim());
            column.setComment(StringUtils.defaultString(hiveColumn.getComment()).trim());
            if(hiveColumn.isPartition()){
                etlInfo.getPartitionKeys().add(column);
            }else{
                etlInfo.getColumns().add(column);
            }
        }

//        String tablePart = pickColumnPart();
//        etlInfo.setColumns(new ColumnParser(tablePart).parse());
//
//        etlInfo.setTableComment(parseTableCommentIfNeed());
//
//
//        if (currentIndex < arrLength - 1) {
//            String pkStr = pickColumnPart();
//            if (StringUtils.isNotBlank(pkStr)) {
//                etlInfo.setPartitionKeys(new ColumnParser(pkStr).parse());
//            }
//        }


        return etlInfo;
    }

    private final static String FIRST_LINE_START = "CREATE TABLE IF NOT EXISTS".replace(" ","");
    private static String wrapIfNeed(String targetDDL){

        List<String> lines = new ArrayList<>();

        for(String line : targetDDL.split("\n")){
            String tmp = line.trim().replace(" ","").replace("\t","");
            if(tmp.toUpperCase().startsWith(FIRST_LINE_START)){
                if(!tmp.contains("`$target.table`")){
                    lines.add(line.replace("$target.table","`$target.table`"));
                    continue;
                }
            }
            lines.add(line);
        }

        return lines.stream().collect(Collectors.joining("\n"));
    }

    private String parseTableCommentIfNeed() {
        String str = new String(chars, currentIndex, arrLength - currentIndex).replace("\n", "").replace("\r", "").trim();
        if (str.toUpperCase().startsWith("COMMENT")) {
            boolean commentStart = false;
            StringBuilder builder = new StringBuilder();
            for (int i = currentIndex; i < arrLength; i++) {
                currentIndex = i;
                char c = chars[i];
                if (c == '\'') {
                    if (!commentStart) {
                        commentStart = true;
                        continue;
                    } else {
                        break;
                    }
                }
                if (commentStart) {
                    builder.append(c);
                }
            }

            currentIndex++;
            return builder.toString();
        }
        return null;
    }

    private String pickColumnPart() {

        boolean inComment = false; // 在注释中

        StringBuilder builder = new StringBuilder();


        outer:
        for (int i = currentIndex; i < arrLength; i++) {

            currentIndex = i;

            char c = chars[i];

            switch (c) {

                case '\'':
                    if (getTopChar() == '(') {
                        // 已经进入列定义的代码块
                        push(c);
                        inComment = true;
                    } else if (getTopChar() == '\'') {
                        // stack栈顶是 == '\'' , 表示注释结束
                        pop();
                        inComment = false;
                    }

                    builder.append(c);
                    break;
                case '"':
                    if (getTopChar() == '(') {
                        // 已经进入列定义的代码块
                        push(c);
                        inComment = true;
                    } else if (getTopChar() == '"') {
                        // stack栈顶是 == '"' , 表示注释结束
                        pop();
                        inComment = false;
                    }

                    builder.append(c);
                    break;

                case '(':
                    if (inComment) {
                        // 注释中
                        builder.append(c);
                    } else {
                        if (getTopChar() == '(') {
                            // ( 已经压栈, 说明当前(不是第一个
                            builder.append(c);
                        }
                        push(c);
                    }

                    break;
                case ')':
                    if (inComment) {
                        builder.append(c);
                    } else {
                        pop();
                        if (getTopChar() == EMPTY) {
                            break outer;
                        } else {
                            builder.append(c);
                        }
                    }
                    break;
                default:
                    if (inComment || getTopChar() == '(') {
                        builder.append(c);
                    }

            }


        }

        clearStack();

        currentIndex++;

        return builder.toString();

    }

    public static void main(String[] args) {
        String ddl =  "CREATE TABLE IF NOT EXISTS `$target.table`\n" +
                "(\n" +
                "    request_id string comment '请求\"id',\n" +
                "    log_time string COMMENT '日志时间',\n" +
                "    action string COMMENT '日志标识', \n" +
                "    utm_content string,\n" +
                "    utm_medium string,\n" +
                "    utm_source string,\n" +
                "    utm_term string,\n" +
                "    utm_campaign string comment '跟踪字段',\n" +
                "    sessionid string COMMENT '行为序列id',\n" +
                "    cityid int ,\n" +
                "    uuid string,\n" +
                "    pos string comment '经纬度',\n" +
                "    offset int comment '翻页信息',\n" +
                "    searchnoresult string comment '是否是搜索无结果，仅针对搜索',\n" +
                "    userid string,\n" +
                "    searchcorrected string comment '是否是搜索纠错，仅针对搜索',    \n" +
                "    traceid string comment '请求唯一id，与前端相关联',\n" +
                "    stid string COMMENT '从stid中提取出来的数字部分',\n" +
                "    stid_param_origin string COMMENT '从stid中提取出来的整体的stid部分',\n" +
                "    item_id string COMMENT 'dealid/poi_id/movie_id/goods_id/card_id/waimaipoi_id/...',\n" +
                "    keywords string COMMENT '关键词',\n" +
                "    stid_param_a string COMMENT '',\n" +
                "    stid_param_c string COMMENT '',\n" +
                "    stid_param_e string COMMENT '',\n" +
                "    stid_param_b string COMMENT '',\n" +
                "    entrance string COMMENT 'b字段中解析得到的：首页或者列表页搜索入口',\n" +
                "    front_cate string COMMENT 'b字段中解析得到的：前台品类',\n" +
                "    input_type string COMMENT '0 手工输入；1 热词输入 2 Suggest输入 3 历史记录 4 Push调用 5 Banner调用 7 相关搜索 8 异地搜索',\n" +
                "    position string COMMENT '列表中排序 poi位置 c字段',\n" +
                "    stid_param_d string COMMENT 'abtest标记',\n" +
                "    global_id string COMMENT 'global_id 单次搜索唯一标识 e字段',\n" +
                "    stid_param_f string COMMENT '所属的POIid',\n" +
                "    stid_param_g string COMMENT '区分是POI详情页下单还是Deal详情页下单',\n" +
                "    stid_param_h string COMMENT '只用作推荐业务',\n" +
                "    stid_param_i string COMMENT '只用作推荐业务',\n" +
                "    stid_param_j string COMMENT '',\n" +
                "    stid_param_k string COMMENT '',\n" +
                "    stid_param_l string COMMENT '购物中心SM的ct_poi的主ID',\n" +
                "    stid_param_m string COMMENT '预留给筛选',\n" +
                "    stid_param_n string COMMENT '商圈和高级筛选项',\n" +
                "    stid_param_o string COMMENT '',\n" +
                "    stid_param_p string COMMENT '只给摘要用，记录是否为动态摘要',\n" +
                "    stid_param_q string COMMENT '预留给筛选顺序的人工干预',\n" +
                "    stid_param_r string comment '登陆状态（在STID后拼登陆状态 _rXX）',\n" +
                "    stid_param_s string COMMENT '圈人用',\n" +
                "    stid_param_t string COMMENT '记录搜索smartbox的id',\n" +
                "    stid_param_u string COMMENT '搜索纠错的id',\n" +
                "    stid_param_v string COMMENT '仅用在美团广告追踪系统',\n" +
                "    stid_server string  COMMENT 'stid_server',\n" +
                "    str string COMMENT 'stid_str',\n" +
                "    item_type string COMMENT 'deal/poi/movie/card/waimai_poi/...',\n" +
                "  \titem_type_recommend string comment '推荐产生的type标记',\n" +
                "    stid_type string COMMENT '',\n" +
                "    stid_strategy string COMMENT '',\n" +
                "  --  is_exposure string COMMENT '是否曝光',\n" +
                "    org_name string COMMENT '事业群名称',\n" +
                "    third_cate_id string COMMENT '后台三级品类id',\n" +
                "  \tthird_cate_name string comment '后台三级品类名',\n" +
                "  \tsecond_cate_id string comment '后台二级品类id',\n" +
                "  \tsecond_cate_name string comment '后台二级品类名',\n" +
                "  \tfirst_cate_id string comment '后台一级品类id',\n" +
                "  \tfirst_cate_name string comment '后台一级品类名',\n" +
                "  \tstid_func string comment '区分业务',\n" +
                "  \tlog_from string comment '日志来源',\n" +
                "  \t`_mt_servername` STRING comment '日志产生服务器',\n" +
                "  \t`abtest_strategys` string comment 'ABTEST策略',\n" +
                "\t`is_crawl` int comment '是否抓取',\n" +
                "\t`location_type` string comment '本异地类型',\n" +
                "\tbu_name string comment '事业部名称',\n" +
                "    bu_id string comment '事业部id'\n" +
                ")\n" +
                "COMMENT '后台api请求明细表'\n" +
                "PARTITIONED BY (\n" +
                "  partition_date STRING COMMENT \"日期分区\",\n" +
                "  partition_log_type string \n" +
                "  )\n" +
                "STORED AS ORC;";

//        try {
//            System.out.println(new ObjectMapper().writeValueAsString(new DDLParser(ddl).parse()));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

//        List<String> parts = new DDLParser(ddl).pickColumnPart();
//        for(String part : parts){
//            try {
//                System.out.println(new ObjectMapper().writeValueAsString(new ColumnParser2(part).parse()));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }

//        String tmpDDL1 = "CREATE TABLE IF NOT EXISTS `$target.table` ( \n" +
//                "    uuid string COMMENT 'uuid' , \n" +
//                "    app_name string COMMENT 'app名称' \n" +
//                ") COMMENT '先锋版mv表(不包括搜索)' \n" +
//                "PARTITIONED BY ( \n" +
//                "    partition_date STRING COMMENT '日期' , \n" +
//                "    partition_app STRING COMMENT 'group,pc,i' , \n" +
//                "    partition_page_position STRING COMMENT 'search-搜索,top-顶部区域,banner-Banner区,icon-频道区,subject-专题区,scene-场景运营,guesslike-猜你喜欢,bottom-底部Tab,operation-运营区,strange-异地,other-其他' , \n" +
//                "    partition_event_type STRING COMMENT '展示|点击事件: view,click,other' \n" +
//                ") \n" +
//                "STORED AS ORC";
//
//        String tmpDDL2 = "CREATE TABLE IF NOT EXISTS $target.table ( \n" +
//                "    uuid string COMMENT 'uuid' , \n" +
//                "    app_name string COMMENT 'app名称' \n" +
//                ") COMMENT '先锋版mv表(不包括搜索)' \n" +
//                "PARTITIONED BY ( \n" +
//                "    partition_date STRING COMMENT '日期' , \n" +
//                "    partition_app STRING COMMENT 'group,pc,i' , \n" +
//                "    partition_page_position STRING COMMENT 'search-搜索,top-顶部区域,banner-Banner区,icon-频道区,subject-专题区,scene-场景运营,guesslike-猜你喜欢,bottom-底部Tab,operation-运营区,strange-异地,other-其他' , \n" +
//                "    partition_event_type STRING COMMENT '展示|点击事件: view,click,other' \n" +
//                ") \n" +
//                "STORED AS ORC";
//
//        String tmpDDL3 = "CREATE TABLE IF NOT EXISTS ` $target . table` ( \n" +
//                "    uuid string COMMENT 'uuid' , \n" +
//                "    app_name string COMMENT 'app名称' \n" +
//                ") COMMENT '先锋版mv表(不包括搜索)' \n" +
//                "PARTITIONED BY ( \n" +
//                "    partition_date STRING COMMENT '日期' , \n" +
//                "    partition_app STRING COMMENT 'group,pc,i' , \n" +
//                "    partition_page_position STRING COMMENT 'search-搜索,top-顶部区域,banner-Banner区,icon-频道区,subject-专题区,scene-场景运营,guesslike-猜你喜欢,bottom-底部Tab,operation-运营区,strange-异地,other-其他' , \n" +
//                "    partition_event_type STRING COMMENT '展示|点击事件: view,click,other' \n" +
//                ") \n" +
//                "STORED AS ORC";
//
//        System.out.println(wrapIfNeed(tmpDDL1));
//        System.out.println(wrapIfNeed(tmpDDL2));
//        System.out.println(wrapIfNeed(tmpDDL3));


        ddl = "CREATE TABLE IF NOT EXISTS `$target.table`\n" +
                "(\n" +
                "    `case_id`                                       bigint  COMMENT '工单ID',\n" +
                "    `lifecycle`                                     bigint  COMMENT '生命周期',\n" +
                "    \n" +
                "    `first_category_id`                             bigint  COMMENT '工单一级问题类别ID',\n" +
                "    `first_category_name`                           string  COMMENT '工单一级问题类别名称',\n" +
                "    `second_category_id`                            bigint  COMMENT '工单二级问题类别ID',\n" +
                "    `second_category_name`                          string  COMMENT '工单二级问题类别名称',\n" +
                "    `third_category_id`                             bigint  COMMENT '工单三级问题类别ID',\n" +
                "    `third_category_name`                           string  COMMENT '工单三级问题类别名称',\n" +
                "    `fourth_category_id`                            bigint  COMMENT '工单四级问题类别ID',\n" +
                "    `fourth_category_name`                          string  COMMENT '工单四级问题类别名称',\n" +
                "    `fifth_category_id`                             bigint  COMMENT '工单五级问题类别ID',\n" +
                "    `fifth_category_name`                           string  COMMENT '工单五级问题类别名称',\n" +
                "  \n" +
                "    `question_id`                                   bigint  COMMENT '工单问题ID',\n" +
                "    `question_name`                                 string  COMMENT '工单问题名称',\n" +
                "    `question_reason_id`                            bigint  COMMENT '问题原因ID',\n" +
                "  \n" +
                "    `case_stage`                                    bigint  COMMENT 'N线工单',\n" +
                "    `min_node_stage`                                bigint  COMMENT '当前生命周期下的最小节点stage（空结点或其它结点4标为-1）',\n" +
                "    `max_node_stage`                                bigint  COMMENT '当前生命周期下的最高节点stage（空结点或其它结点4标为-1）',\n" +
                "    `first_node_stage`                              bigint  COMMENT '当前生命周期起始时的stage',\n" +
                "    `last_node_stage`                               bigint  COMMENT '当前生命周期结束时的stage',\n" +
                "  \n" +
                "    `start_time`                                    string  COMMENT '开单时间',\n" +
                "    `close_time`                                    string  COMMENT '关单时间',\n" +
                "    `source`                                        bigint  COMMENT '来源渠道: 1电话 2在线 3工作单 4模拟弹屏创建',\n" +
                "  \n" +
                "    `is_user_anonymous`                             bigint  COMMENT '是否用户匿名',\n" +
                "    `is_bind_order`                                 bigint  COMMENT '是否关联订单',\n" +
                "\n" +
                "    `is_urge`                                       bigint  COMMENT '是否有催单',\n" +
                "    `urge_cnt`                                      bigint  COMMENT '催单次数',\n" +
                "    `is_other_urge`                                 bigint  COMMENT '是否有非关单人催单',\n" +
                "    `is_other_comment`                              bigint  COMMENT '是否有非关单人添加备注',\n" +
                "    `is_other_urge_and_comment`                     bigint  COMMENT '是否有非关单人催单or添加备注',\n" +
                "    \n" +
                "    `chat__newest_staff_service_id`                 bigint  COMMENT '在线chat-最新坐席服务ID',\n" +
                "    `chat__staff_service_cnt`                       bigint  COMMENT '在线chat-坐席服务数',\n" +
                "    `chat__no_trans_out_staff_service_cnt`          bigint  COMMENT '在线chat-坐席非转出会话服务数',\n" +
                "    `call_in__newest_ori_contact_id`                string  COMMENT '电话呼入-最新原始联络标识',\n" +
                "    `call_in__contact_cnt`                          bigint  COMMENT '电话呼入-呼入次数',\n" +
                "    `call_out__newest_ori_contact_id`               string  COMMENT '电话呼出-最新原始联络标识',\n" +
                "    `call_out__contact_cnt`                         bigint  COMMENT '电话呼出-呼出次数',\n" +
                "    `contact_cnt`                                   bigint  COMMENT '联络次数(坐席非转出会话服务数+呼入次数)',\n" +
                "    `is_contact_cnt_gt1`                            bigint  COMMENT '是否联络次数大于1(联络次数：坐席非转出会话服务数+呼入次数)',\n" +
                "    `has_interaction`                               bigint  COMMENT '是否有过交互（外呼、线下大象、线下邮件QQ等，本期先考虑外呼）',\n" +
                "  \n" +
                "  \n" +
                "    `is_fcs`                                        bigint  COMMENT '是否首次解决(1.是 0.否)',\n" +
                "    `is_upgrade`                                    bigint  COMMENT '是否升级(1.是 0.否)',\n" +
                "    `is_1_to_2_upgrade`                             bigint  COMMENT '是否1线节点到2线节点升级',\n" +
                "    `is_1_to_3_upgrade`                             bigint  COMMENT '是否1线节点到3线节点升级',\n" +
                "    `is_1_to_4_upgrade`                             bigint  COMMENT '是否1线节点到4线节点升级',\n" +
                "    `is_2_to_3_upgrade`                             bigint  COMMENT '是否2线节点到3线节点升级',\n" +
                "    `is_2_to_4_upgrade`                             bigint  COMMENT '是否2线节点到4线节点升级',\n" +
                "    `is_3_to_4_upgrade`                             bigint  COMMENT '是否3线节点到4线节点升级',\n" +
                "    `is_upgrade_error`                              bigint  COMMENT '是否升级错误(1.是 0.否)',\n" +
                "    `is_create_then_close`                          bigint  COMMENT '是否创建即关单(1.是 0.否)',\n" +
                "    `is_stage_upgrade`                              bigint  COMMENT '是否N线升级（stage升级）',\n" +
                "    `is_sluggish`                                   bigint  COMMENT '是否迟滞',\n" +
                "  \n" +
                "  \n" +
                "    `trans_node_cnt`                                bigint  COMMENT '流转节点数',\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "    `close_node_status_id`                          bigint  COMMENT '工单结案前状态ID',\n" +
                "    `close_node_status_name`                        string  COMMENT '工单结案前状态名称',\n" +
                "  \n" +
                "    `is_1_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单1天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_2_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单2天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_3_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单3天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_4_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单4天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_5_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单5天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_6_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单6天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    `is_7_day_third_cate_change_same_user_create`   bigint  COMMENT '工单关单7天内是否有同用户ID下的同三级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单1天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_2_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单2天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_3_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单3天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_4_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单4天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_5_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单5天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_6_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单6天内是否有同用户ID下的同五级问题类型工单',\n" +
                "    `is_7_day_fifth_cate_change_same_user_create`   bigint  COMMENT '工单关单7天内是否有同用户ID下的同五级问题类型工单',\n" +
                "  \n" +
                "    `is_reopen_until_now`                           bigint  COMMENT '全局至今是否有重开(1.有重开 0.无重开)',\n" +
                "    `reopen_time`                                   string  COMMENT '工单重开时间',\n" +
                "    `reopen_staff_id`                               bigint  COMMENT '工单重开人ID',\n" +
                "    `reopen_staff_login_name`                       string  COMMENT '工单重开人登录名',\n" +
                "    `reopen_staff_real_name`                        string  COMMENT '工单重开人姓名',\n" +
                "    `reopen_staff_dep_name`                         string  COMMENT '工单重开人部门名称',\n" +
                "  \n" +
                "    `is_1_day_reopen`                               bigint  COMMENT '是否1天重开(1.有重开 0.无重开)',\n" +
                "    `is_2_day_reopen`                               bigint  COMMENT '是否2天重开(1.有重开 0.无重开)',\n" +
                "    `is_3_day_reopen`                               bigint  COMMENT '是否3天重开(1.有重开 0.无重开)',\n" +
                "    `is_4_day_reopen`                               bigint  COMMENT '是否4天重开(1.有重开 0.无重开)',\n" +
                "    `is_5_day_reopen`                               bigint  COMMENT '是否5天重开(1.有重开 0.无重开)',\n" +
                "    `is_6_day_reopen`                               bigint  COMMENT '是否6天重开(1.有重开 0.无重开)',\n" +
                "    `is_7_day_reopen`                               bigint  COMMENT '是否7天重开(1.有重开 0.无重开)',\n" +
                "    `is_third_cate_id_change`                       bigint  COMMENT '重开后三级问题类型有无变化(1.有变化 0.无变化)',\n" +
                "    `is_fifth_cate_id_change`                       bigint  COMMENT '重开后五级问题类型有无变化(1.有变化 0.无变化)',\n" +
                "  \n" +
                "    `reopen_type_1_day`                             string  COMMENT '1天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_2_day`                             string  COMMENT '2天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_3_day`                             string  COMMENT '3天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_4_day`                             string  COMMENT '4天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_5_day`                             string  COMMENT '5天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_6_day`                             string  COMMENT '6天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "    `reopen_type_7_day`                             string  COMMENT '7天重开类型(\"真重开\",\"伪重开\",\"无重开\")',\n" +
                "\n" +
                "    `has_sku_id`\t\t\t\t\t\t\t\t    bigint  COMMENT  '是否有项目(1.是 0.否)', \n" +
                "    `is_1_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单1天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_2_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单2天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_3_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单3天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_4_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单4天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_5_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单5天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_6_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单6天内是否有同SKU下的同三级问题类型工单',\n" +
                "    `is_7_day_third_cate_change_same_sku_open`      bigint  COMMENT  '工单关单7天内是否有同SKU下的同三级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单1天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_2_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单2天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_3_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单3天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_4_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单4天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_5_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单5天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_6_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单6天内是否有同SKU下的同五级问题类型工单',\n" +
                "    `is_7_day_fifth_cate_change_same_sku_open`      bigint  COMMENT  '工单关单7天内是否有同SKU下的同五级问题类型工单',\n" +
                "  \n" +
                "    `has_poi_id`                                    bigint  COMMENT  '是否有POIID(1.是 0.否)',              \n" +
                "    `is_1_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单1天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_2_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单2天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_3_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单3天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_4_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单4天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_5_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单5天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_6_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单6天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    `is_7_day_third_cate_change_same_poi_open`      bigint  COMMENT  '工单关单7天内是否有同商家ID下的同三级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单1天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_2_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单2天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_3_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单3天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_4_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单4天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_5_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单5天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_6_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单6天内是否有同商家ID下的同五级问题类型工单',\n" +
                "    `is_7_day_fifth_cate_change_same_poi_open`      bigint  COMMENT  '工单关单7天内是否有同商家ID下的同五级问题类型工单',\n" +
                "  \n" +
                "    `is_1_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单1天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_2_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单2天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_3_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单3天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_4_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单4天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_5_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单5天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_6_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单6天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    `is_7_day_third_cate_change_same_phone_open`    bigint  COMMENT  '工单关单7天内是否有同来电号码下的同三级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单1天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_2_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单2天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_3_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单3天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_4_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单4天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_5_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单5天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_6_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单6天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    `is_7_day_fifth_cate_change_same_phone_open`    bigint  COMMENT  '工单关单7天内是否有同来电号码下的同五级问题类型工单',\n" +
                "    \n" +
                "    -- 20200710新增工单关单n天内是否有同订单ID下的同三级问题类型\n" +
                "    `has_order_id`                                  bigint  COMMENT  '是否有订单ID(1.是 0.否 NULL.未结案)',\n" +
                "    `is_1_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单1天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_2_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单2天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_3_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单3天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_4_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单4天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_5_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单5天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_6_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单6天内是否有同订单下的同三级问题类型工单',\n" +
                "    `is_7_day_third_cate_change_same_order_open`    bigint  COMMENT  '工单关单7天内是否有同订单下的同三级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单1天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_2_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单2天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_3_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单3天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_4_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单4天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_5_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单5天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_6_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单6天内是否有同订单下的同五级问题类型工单',\n" +
                "    `is_7_day_fifth_cate_change_same_order_open`    bigint  COMMENT  '工单关单7天内是否有同订单下的同五级问题类型工单',\n" +
                "   \n" +
                "    `same_order_open_cnt_1_day`                     bigint  COMMENT  '工单关单1天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_2_day`                     bigint  COMMENT  '工单关单2天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_3_day`                     bigint  COMMENT  '工单关单3天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_4_day`                     bigint  COMMENT  '工单关单4天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_5_day`                     bigint  COMMENT  '工单关单5天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_6_day`                     bigint  COMMENT  '工单关单6天内有同订单下的工单数量',\n" +
                "    `same_order_open_cnt_7_day`                     bigint  COMMENT  '工单关单7天内有同订单下的工单数量',\n" +
                "    \n" +
                "    -- 20200710新增是否有用户ID，1-7天是有同用户/SKU/POI/来电号码的工单数量\n" +
                "    `has_user_id`                                   bigint  COMMENT  '是否有用户ID(1.是 0.否 NULL.未结案)',\n" +
                "    `same_user_open_cnt_1_day`                      bigint  COMMENT  '工单关单1天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_2_day`                      bigint  COMMENT  '工单关单2天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_3_day`                      bigint  COMMENT  '工单关单3天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_4_day`                      bigint  COMMENT  '工单关单4天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_5_day`                      bigint  COMMENT  '工单关单5天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_6_day`                      bigint  COMMENT  '工单关单6天内有同用户ID下的工单数量',\n" +
                "    `same_user_open_cnt_7_day`                      bigint  COMMENT  '工单关单7天内有同用户ID下的工单数量',\n" +
                "    \n" +
                "    `same_sku_open_cnt_1_day`                       bigint  COMMENT  '工单关单1天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_2_day`                       bigint  COMMENT  '工单关单2天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_3_day`                       bigint  COMMENT  '工单关单3天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_4_day`                       bigint  COMMENT  '工单关单4天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_5_day`                       bigint  COMMENT  '工单关单5天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_6_day`                       bigint  COMMENT  '工单关单6天内有同SKU下的工单数量',\n" +
                "    `same_sku_open_cnt_7_day`                       bigint  COMMENT  '工单关单7天内有同SKU下的工单数量',\n" +
                "    \n" +
                "    `same_poi_open_cnt_1_day`                       bigint  COMMENT  '工单关单1天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_2_day`                       bigint  COMMENT  '工单关单2天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_3_day`                       bigint  COMMENT  '工单关单3天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_4_day`                       bigint  COMMENT  '工单关单4天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_5_day`                       bigint  COMMENT  '工单关单5天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_6_day`                       bigint  COMMENT  '工单关单6天内有同商家ID下的工单数量',\n" +
                "    `same_poi_open_cnt_7_day`                       bigint  COMMENT  '工单关单7天内有同商家ID下的工单数量',\n" +
                "    \n" +
                "    `has_phone_id`                                  bigint  COMMENT  '是否有来电号码(1.是 0.否 NULL.未结案)',\n" +
                "    `same_phone_open_cnt_1_day`                     bigint  COMMENT  '工单关单1天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_2_day`                     bigint  COMMENT  '工单关单2天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_3_day`                     bigint  COMMENT  '工单关单3天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_4_day`                     bigint  COMMENT  '工单关单4天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_5_day`                     bigint  COMMENT  '工单关单5天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_6_day`                     bigint  COMMENT  '工单关单6天内有同来电号码下的工单数量',\n" +
                "    `same_phone_open_cnt_7_day`                     bigint  COMMENT  '工单关单7天内有同来电号码下的工单数量',\n" +
                "    \n" +
                "    `is_1_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单1天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_2_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单2天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_3_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单3天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_4_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单4天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_5_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单5天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_6_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单6天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    `is_7_day_first_cate_change_same_user_create`   bigint  COMMENT '工单关单7天内是否有同用户ID下的同一级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单1天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_2_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单2天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_3_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单3天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_4_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单4天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_5_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单5天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_6_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单6天内是否有同SKU下的同一级问题类型工单',\n" +
                "    `is_7_day_first_cate_change_same_sku_open`      bigint  COMMENT  '工单关单7天内是否有同SKU下的同一级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单1天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_2_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单2天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_3_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单3天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_4_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单4天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_5_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单5天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_6_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单6天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    `is_7_day_first_cate_change_same_poi_open`      bigint  COMMENT  '工单关单7天内是否有同商家ID下的同一级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单1天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_2_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单2天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_3_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单3天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_4_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单4天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_5_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单5天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_6_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单6天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    `is_7_day_first_cate_change_same_phone_open`    bigint  COMMENT  '工单关单7天内是否有同来电号码下的同一级问题类型工单',\n" +
                "    \n" +
                "    `is_1_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单1天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_2_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单2天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_3_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单3天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_4_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单4天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_5_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单5天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_6_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单6天内是否有同订单下的同一级问题类型工单',\n" +
                "    `is_7_day_first_cate_change_same_order_open`    bigint  COMMENT  '工单关单7天内是否有同订单下的同一级问题类型工单',\n" +
                "    \n" +
                "    `client_urge_cnt`                               bigint  COMMENT  '用户在客户端发起催单次数',\n" +
                "    `contact_urge_cnt`                              bigint  COMMENT  '联络次数+催单次数,用于判断一解首解字段',\n" +
                "    \n" +
                "    `deadline`                                      string  COMMENT  '截止时间',\n" +
                "    \n" +
                "    `call_in_user_cnt`                              bigint COMMENT '电话呼入-用户来电次数',                       \n" +
                "   `call_in_business_cnt`                          bigint COMMENT '电话呼入-商家来电次数',\n" +
                "   \n" +
                "   `reopen_type_3_day_hotel`                        string  COMMENT '住宿高星是否3天重开(1.有重开 0.无重开)',\n" +
                "   `case_lifecycle_type`                            string COMMENT '生命周期类型:用户/商家/其他',\n" +
                "   \n" +
                "   `is_3_day_third_cate_change_same_order_open_hotel`   bigint  COMMENT '工单关单3天内是否有同订单下的同三级问题类型工单(住宿)',\n" +
                "   `is_3_day_fifth_cate_change_same_order_open_hotel`   bigint  COMMENT '工单关单3天内是否有同订单下的同五级问题类型工单(住宿)',\n" +
                "   `is_3_day_third_cate_change_same_user_create_hotel`  bigint  COMMENT '工单关单3天内是否有同用户ID下的同三级问题类型工单(住宿)',\n" +
                "   `is_3_day_fifth_cate_change_same_user_create_hotel`  bigint  COMMENT '工单关单3天内是否有同用户ID下的同五级问题类型工单(住宿)',\n" +
                "   `is_3_day_third_cate_change_same_phone_open_hotel`   bigint  COMMENT '工单关单3天内是否有同来电号码下的同三级问题类型工单(住宿)',\n" +
                "   `is_3_day_fifth_cate_change_same_phone_open_hotel`   bigint  COMMENT '工单关单3天内是否有同来电号码下的同五级问题类型工单(住宿)'\n" +
                "    \n" +
                "    \n" +
                ") COMMENT '太平洋工单生命周期衍生指标因子表'\n" +
                "PARTITIONED BY (\n" +
                "  `partition_is_closed`  bigint COMMENT '是否已关单(1.是 0.否)',\n" +
                "  `partition_close_date` string COMMENT '关单日期分区(未关单为9999-12-31)'\n" +
                ") STORED AS ORC;";

        try {
            System.out.println(new ObjectMapper().writeValueAsString(new DDLParser(ddl).parse()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
