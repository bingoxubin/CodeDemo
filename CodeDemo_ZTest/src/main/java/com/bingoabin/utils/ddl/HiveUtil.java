package com.bingoabin.utils.ddl;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouzhenwei
 * @ClassName HiveUtil
 * @Description
 * @createTime 2021-07-29 17:51:59
 */
public class HiveUtil {
    private static ParseDriver PARSE_DRIVER = new ParseDriver();
    private static String COMMENT = "##.*?\n";
    private static String REPLACE = "\n";
    private static String END = "(;| )$";
    private static String TOK = "TOK_";
    private static String ARRAY = "ARRAY";
    private static String COMMA = ",";
    private static String COLON = ":";
    private static String LEFT = "(";
    private static String RIGHT = ")";
    private static String LESS = "<";
    private static String MORE = ">";
    private static String BLANK = "";
    private static String QUOTEB = "`";
    private static String QUOTES = "'";
    private static String QUOTED = "\"";
    private static String SQL = "CREATE TABLE IF NOT EXISTS `$target.table`\n" +
            "(\n" +
            "  event_identifier          string COMMENT '日志唯一标识',\n" +
            "  event_timestamp           bigint COMMENT '用户行为操作时间戳(毫秒)',\n" +
            "  stat_time                 string COMMENT '用户行为操作时间(yyyyMMdd HH:mm:ss)',\n" +
            "  user_id                   bigint COMMENT '用户id',\n" +
            "  guess_user_id             bigint COMMENT '推测用户id',\n" +
            "  device_id                 string COMMENT 'uuid和dpid',\n" +
            "  device_type               string COMMENT '设备类型',\n" +
            "  app_version               string COMMENT 'app版本',\n" +
            "  app_name                  string COMMENT 'app名称',\n" +
            "  session_id                string COMMENT '会话id',\n" +
            "  union_id                  string COMMENT '美团点评统一设备ID',\n" +
            "  utm                       string COMMENT 'utm信息(包含utm_source,utm_medium,utm_campaign,utm_content,utm_term)',\n" +
            "  url                       string COMMENT 'url',\n" +
            "  refer_url                 string COMMENT '上个页面url',\n" +
            "  launch_channel            string COMMENT 'app启动渠道',\n" +
            "  push_id                   string COMMENT '推送标识',\n" +
            "  page_stay_time            bigint COMMENT '页面访问时长',\n" +
            "  locate_city_id            int    COMMENT '定位城市id',\n" +
            "  locate_city_name          string COMMENT '定位城市名称',\n" +
            "  latitude                  double COMMENT '纬度',\n" +
            "  longitude                 double COMMENT '经度',\n" +
            "  page_city_id              int    COMMENT '页面城市id',\n" +
            "  page_city_name            string COMMENT '页面城市名称',\n" +
            "  res_city_id               int    COMMENT '注册城市id',\n" +
            "  res_city_name             string COMMENT '注册城市名称',\n" +
            "  page_identifier           string comment '页面上报标识id',\n" +
            "  page_id                   int    COMMENT '页面id,对应于埋点配置平台页面的page_id',\n" +
            "  page_name                 string COMMENT '页面配置名称',\n" +
            "  page_bg                   string COMMENT '页面配置事业群',\n" +
            "  page_biz                  string COMMENT '页面配置标签',\n" +
            "  refer_page_identifier     string comment '来源页上报标识',\n" +
            "  refer_page_id             bigint COMMENT '来源页面id',\n" +
            "  refer_page_name           string COMMENT '来源页面名称',\n" +
            "  tag                       string COMMENT 'Tag标记(用于追踪入口策略信息)',\n" +
            "  custom                    MAP<string,string> COMMENT '上报参数(PV上报参数)',\n" +
            "  extension                 MAP<string,string> COMMENT '扩展字段',\n" +
            "  is_native                 bigint COMMENT '是否是native(0:web上报,1:native上报,2:native方式上报web内容,3:小程序上报)',\n" +
            "  geo_city_id               int    COMMENT '用户定位城市(由经纬度计算得出)',\n" +
            "  ip_location_city_id       int    COMMENT 'IP定位城市',\n" +
            "  request_id                string COMMENT '请求id',\n" +
            "  refer_request_id          string COMMENT '来源请求id',\n" +
            "  page_type                 string COMMENT '页面功能类型',\n" +
            "  geohash\t                string COMMENT '地理hashcode',\n" +
            "  partition_platform        string COMMENT '平台mt,dp,other',\n" +
            "  tag_info                  MAP<string,string> COMMENT '平台自定义tag信息',\n" +
            "  date                   string COMMENT '平台业务bu'\n" +
            "\n" +
            "\n" +
            ")\n" +
            "COMMENT '美团点评PV宽表'\n" +
            "PARTITIONED BY (\n" +
            "  partition_date            string COMMENT '日志生成日期',\n" +
            "  partition_app             string COMMENT '应用名',\n" +
            "  is_intention              bigint COMMENT '是否是意向页' ,\n" +
            "  partition_log_channel     string COMMENT '日志通道'\n" +
            ")\n" +
            "STORED AS ORC;";


    public static void main(String[] args) throws ParseException {
        HiveTable table = getTable(SQL);
        System.out.println(table.toString());
    }

    public static HiveTable getTable(String sql) throws ParseException {
        HiveTable table = new HiveTable();
        String temp = caseByCase(sql);
        ASTNode createTable = getCreateTableNode(temp);
        table.setComment(getComment(createTable));
        table.setColumns(getColumns(createTable));
        return table;
    }

    private static String caseByCase(String sql) {
        return sql.replaceAll("\\sdate( )+string( )+COMMENT", "`date` STRING COMMENT");
    }

    private static String getComment(ASTNode node) {
        if (node != null) {
            for (Node child : node.getChildren()) {
                ASTNode temp = (ASTNode) child;
                if (temp.getToken().getType() == HiveParser.TOK_TABLECOMMENT
                        && CollectionUtils.isNotEmpty(temp.getChildren())) {
                    return getPlain(getText(temp.getChildren(), 0));
                }
            }
        }
        return null;
    }

    public static List<HiveColumn> getColumns(ASTNode node) {
        List<HiveColumn> columns = Lists.newArrayList();
        getColumns(columns, node);
        return columns;
    }


    private static ASTNode getCreateTableNode(String sql) throws ParseException {
        if (StringUtils.isNotBlank(sql)) {
            ASTNode ast = PARSE_DRIVER.parse(sql.replaceAll(COMMENT, REPLACE).replaceAll(END, BLANK));
            if (ast != null && ast.getToken() == null && ast.getChildCount() > 0) {
                for (Node child : ast.getChildren()) {
                    ASTNode node = (ASTNode) child;
                    if (HiveParser.TOK_CREATETABLE == node.getToken().getType()) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    private static void getColumns(List<HiveColumn> cols, ASTNode node) {
        if (node != null) {
            if (HiveParser.TOK_TABCOL == node.getToken().getType()) {
                ArrayList<Node> children = node.getChildren();
                HiveColumn column = new HiveColumn();
                column.setName(getPlain(getText(children, 0)));
                StringBuilder sb = new StringBuilder();
                getType(sb, (ASTNode) children.get(1));
                column.setType(sb.toString().toLowerCase());
                if (children.size() > 2) {
                    column.setComment(getPlain(getText(children, 2)));
                } else {
                    column.setComment(BLANK);
                }
                if (node.getParent() != null && node.getParent().getParent() != null
                        && node.getParent().getParent().getType() == HiveParser.TOK_TABLEPARTCOLS) {
                    column.setPartition(true);
                }
                cols.add(column);
            } else {
                if (CollectionUtils.isNotEmpty(node.getChildren())) {
                    for (Node child : node.getChildren()) {
                        getColumns(cols, (ASTNode) child);
                    }
                }
            }
        }
    }

    private static String getText(ArrayList<Node> children, int index) {
        return ((ASTNode) children.get(index)).getToken().getText();
    }

    private static void getType(StringBuilder sb, ASTNode node) {
        int type = node.getToken().getType();
        String text = node.getToken().getText().replace(TOK, BLANK);
        ArrayList<Node> children = node.getChildren();
        if (HiveParser.TOK_TABCOL == type) {
            sb.append(((ASTNode) node.getChildren().get(0)).getToken().getText().replace(TOK, BLANK)).append(COLON);
            getType(sb, (ASTNode) node.getChildren().get(1));
        } else {
            switch (type) {
                case HiveParser.TOK_DECIMAL:
                    if (CollectionUtils.isNotEmpty(children) && children.size() > 1) {
                        sb.append(text).append(LEFT)
                                .append(((ASTNode) children.get(0)).getToken().getText()).append(COMMA)
                                .append(((ASTNode) children.get(1)).getToken().getText())
                                .append(RIGHT);
                    }
                    break;
                case HiveParser.TOK_LIST:
                    if (CollectionUtils.isNotEmpty(children) && children.size() > 0) {
                        sb.append(ARRAY).append(LESS);
                        getType(sb, (ASTNode) children.get(0));
                        sb.append(MORE);
                    }
                    break;
                case HiveParser.TOK_MAP:
                    if (CollectionUtils.isNotEmpty(children) && children.size() > 1) {
                        sb.append(text).append(LESS);
                        getType(sb, (ASTNode) children.get(0));
                        sb.append(COMMA);
                        getType(sb, (ASTNode) children.get(1));
                        sb.append(MORE);
                    }
                    break;
                case HiveParser.TOK_STRUCT:
                    if (CollectionUtils.isNotEmpty(children) && children.size() > 0) {
                        sb.append(text).append(LESS);
                        int flag = children.get(0).getChildren().size();
                        for (Node child : children.get(0).getChildren()) {
                            getType(sb, (ASTNode) child);
                            if (flag != 1) {
                                sb.append(COMMA);
                            }
                            flag--;
                        }
                        sb.append(MORE);
                    }
                    break;
                default:
                    sb.append(text);
            }
        }
    }

    private static String getPlain(String text) {
        if (StringUtils.isNotBlank(text) && text.length() > 1) {
            if (text.indexOf(QUOTES) == 0 && text.lastIndexOf(QUOTES) == text.length() - 1) {
                return text.substring(1, text.length() - 1);
            } else if (text.indexOf(QUOTED) == 0 && text.lastIndexOf(QUOTED) == text.length() - 1) {
                return text.substring(1, text.length() - 1);
            } else if (text.indexOf(QUOTEB) == 0 && text.lastIndexOf(QUOTEB) == text.length() - 1) {
                return text.substring(1, text.length() - 1);
            } else {
                return text;
            }
        }
        return BLANK;
    }
}
