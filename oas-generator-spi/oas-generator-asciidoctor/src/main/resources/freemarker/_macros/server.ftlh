<#ftl auto_esc=false>
<#--
    Macro for a table entry of a single server
-->
<#macro server server>
2+h|`${server.url}`
|<#if server.description??>${server.description}</#if>
a|<#if server.variables??>
<#list server.variables as name, variable>
${name}::
Default: `${variable.defaultValue}`<#if variable.enumValue??> (Allowed: <#list variable.enumValue as value>`${value}`<#sep>, </#list>)</#if>
<#if variable.description?? && variable.description?has_content>
+
_${variable.description}_
</#if>
</#list>
</#if>
</#macro>
