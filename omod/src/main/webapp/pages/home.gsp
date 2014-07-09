<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeJavascript("uicommons", "navigator/validators.js", Integer.MAX_VALUE - 19)
    ui.includeJavascript("uicommons", "navigator/navigator.js", Integer.MAX_VALUE - 20)
    ui.includeJavascript("uicommons", "navigator/navigatorHandlers.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/navigatorModels.js", Integer.MAX_VALUE - 21)
    ui.includeJavascript("uicommons", "navigator/exitHandlers.js", Integer.MAX_VALUE - 22);
%>
${ ui.includeFragment("uicommons", "validationMessages")}

<script type="text/javascript">
    var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${ ui.message("burdetteportal.app.title") }", link: "${ ui.pageLink("burdetteportal", "home") }" }
    ];

    jQuery(document).ready(function(){
        jQuery("#portal").attr("src", "/" + OPENMRS_CONTEXT_PATH + "/module/burdetteportal/portal.htm");
    });
</script>

<style>
    #portal {
        width: 100%;
        height: 800px;
        border: none;
    }
</style>

<div id="content" class="container">
    <iframe id="portal"></iframe>
</div>
