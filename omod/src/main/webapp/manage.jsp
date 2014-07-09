<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<style>
    fieldset {
        padding: 1em;
    }

    p.input-position-class label {
        display: inline-block;
        width: 6em;
    }

    p.input-position-class input {
        width: 40em;
        line-height: 2em;
        padding: 0 0.5em;
    }

    input[type="submit"] {
        line-height: 2em;
    }
</style>

<form method="post">
    <fieldset>
        <legend>
            Login Settings
        </legend>
        <p class="input-position-class">
            <label name="input-url">URL</label>
            <input type="text" id="input-url" name="url" value="${url}" placeholder="Enter URL"/>
        </p>
        <p class="input-position-class">
            <label name="input-username">Username</label>
            <input type="text" id="input-username" name="username" value="${username}" placeholder="Enter Username"/>
        </p>
        <p class="input-position-class">
            <label name="input-password">Password</label>
            <input type="password" id="input-password" name="password" placeholder="Leave blank if no change needed"/>
        </p>
        <input type="submit" value="Update Settings"/>
    </fieldset>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>