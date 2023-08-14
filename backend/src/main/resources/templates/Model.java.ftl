package ${packageName}.model;

public class ${modelName}{
<#if columns??>
    <#list columns as column>
        /**
        * ${column.remark}
        */
        private ${column.type} ${column.propertyName?uncap_first};
    </#list>
</#if>
}
