package ${classPath};

public class ${className} {
<#list attributes as attribute>
    private ${attribute.type} ${attribute.name};
</#list>

}
