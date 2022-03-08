package com.bingoabin.utils.ddl;

import com.bingoabin.utils.exception.DDLCreateException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ouzhenwei
 * @ClassName DDLCreator
 * @Description
 * @createTime 2021-07-30 17:17:07
 */
public class DDLCreator {

    private final static Logger logger = LoggerFactory.getLogger(DDLCreator.class);

    private final static String FTL_PATH = "tpl";

    private final static String FTL_NAME = "ddl.ftl";

    private final static String DDL_MODEL_TEMPLATE = "##TargetDDL## \n" +
            "CREATE TABLE IF NOT EXISTS `$target.table` ( \n" +
            "<#if columns?? && columns?size gt 0> \n" +
            "    <#list columns as item> \n" +
            "    ${(item.name)!} ${(item.type)!} COMMENT '${(item.comment)!}' <#sep>, \n</#sep>\n" +
            "    </#list> \n" +
            "</#if> \n" +
            "\n) COMMENT '${(tblComment)!}' \n" +
            "<#if partitions?? && partitions?size gt 0> \n" +
            "PARTITIONED BY ( \n" +
            "    <#list partitions as item> \n" +
            "    `${(item.name)!}` ${(item.type)!} COMMENT '${(item.comment)!}' <#sep>, \n</#sep>\n" +
            "    </#list> \n" +
            "\n) \n" +
            "</#if> \n" +
            "STORED AS ORC";

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    private DDLCreator(){
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("DDL_MODEL_TEMPLATE", DDL_MODEL_TEMPLATE);
        cfg.setTemplateLoader(stringLoader);
    }

    public static final DDLCreator INSTANCE = new DDLCreator();


    public String create(DDLInfo info) throws DDLCreateException {

        if(null == info){
            return "";
        }

        List<DDLColumn> columns = info.getColumns();

        if(null == columns){
            return "";
        }

        StringWriter writer = new StringWriter();

        try {
            Template template = cfg.getTemplate("DDL_MODEL_TEMPLATE", "UTF-8");

            Map<String,Object> root = new HashMap<>();

            List<DDLColumn> columnList = columns.stream().filter(c -> !c.isAsPartitionKey()).collect(Collectors.toList());
            columnList.forEach(column -> {
                column.setName(this.setColumnNameFormat(column.getName()));
                column.setType(this.setColumnTypeFormat(column.getType()));
            });
            List<DDLColumn> partitionList = columns.stream().filter(DDLColumn::isAsPartitionKey).collect(Collectors.toList());

            root.put("columns",columnList);
            root.put("partitions",partitionList);
            root.put("tblComment", info.getTblComment());


            template.process(root,writer);

            String rst = writer.toString();

            return rst;
        }catch (IOException | TemplateException e){
            throw new DDLCreateException(e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String setColumnNameFormat(String columnName){
        columnName = String.format("%s%s%s","`",columnName,"`");
        return String.format("%-32s",columnName);
    }

    public static String setColumnTypeFormat(String columnType){
        return String.format("%-10s",columnType);
    }

}
