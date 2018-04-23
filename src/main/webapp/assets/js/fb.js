window.fbAsyncInit = function() {
    FB.init({
        appId : '880431152117766',
        cookie : true,
        xfbml : true,
        version : 'v2.12'
    });
    FB.AppEvents.logPageView();
    FB.Event.subscribe('auth.login', login_event);
};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

var login_event = function(response) {
    getAppAccessToken(response);
}

function loginToWebApp(response) {
    $('#login').find('input[name=email]').val(response.user.email);
    $('#login').find('input[name=password]').val(response.user.password);
    $('#login').submit();
}

function getAppAccessToken(response) {
    $.ajax({
        url : getContextPath() + '/oauths?access_token=' + response.authResponse.accessToken,
        dataType : 'json',
        success : function(response) {
            if (response.result)
                loginToWebApp(response);
        },
    });
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
