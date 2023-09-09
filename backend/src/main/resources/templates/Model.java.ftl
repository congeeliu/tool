package ${packageName}.model;

public class ${modelName}{
<#if properties??>
    <#list properties as property>
        private ${column.propertyType} ${column.propertyName?uncap_first};
    </#list>
</#if>
}
