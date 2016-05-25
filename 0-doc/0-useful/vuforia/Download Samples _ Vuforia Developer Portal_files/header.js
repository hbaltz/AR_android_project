/*
 * ========================================================================================
 *   Copyright (c) 2012-2015 Qualcomm Connected Experiences, Inc. All Rights Reserved.
 *
 *   Confidential and Proprietary - Qualcomm Connected Experiences, Inc.
 *   Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States and
 *   other countries. Trademarks of QUALCOMM Incorporated are used with permission.
 * =========================================================================================
 */

var vuforiaUser = null;
var webServiceErrorMessage="There has been an error while processing your request. Please try again or search for the issue in the forums.";
var userName;
var isAdminAccess = false;


$(document).ready(function(){
    $('.login-user a.dropdown-toggle').on('click', function () {
        var loginMenuW = $(".login-user").width();
        $(".login-user .dropdown-menu").css("width",loginMenuW+"px");
    });

    $.ajax({
        type: "GET",
        url: contextPath + "/vuforiaUtil/getLoggedInUser",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            if (!$.isEmptyObject(result)) {
                try {
                    vuforiaUser = result;
                    sessionStorage["username"] = vuforiaUser.username;
                    sessionStorage["register"] = "false";
                    $("#loginRegisterDiv").hide(0);
                    $("#loggedInLogoutDiv").show(0);
                    $(".userNameInHeader").html("Hello " + getStringOfFixedLength(vuforiaUser.username, 17));
                    $('.userNameInHeader').attr('title', vuforiaUser.username);
                    createAllAdminTabs();
                    initializeDrupalLinks();
                    generateTMSDevelopTabs();
                }catch(err) {
                    sessionStorage.clear();
                    $("#loggedInLogoutDiv").hide(0);
                    $("#loginRegisterDiv").show(0);
                    generateDrupalDevelopTabs();
                    sessionStorage["register"] = "true";
                }
            }else {
                sessionStorage.clear();
                $("#loggedInLogoutDiv").hide(0);
                $("#loginRegisterDiv").show(0);
                generateDrupalDevelopTabs();
                sessionStorage["register"] = "true";
            }
        },error: function(e){
            sessionStorage.clear();
            $("#loggedInLogoutDiv").hide(0);
            $("#loginRegisterDiv").show(0);
            generateDrupalDevelopTabs();
            sessionStorage["register"] = "true";
        }
    });


    /* Get Count Of Unread Messages */
    $.ajax({
        type: "GET",
        url: contextPath + "/vuforiaUtil/getUnreadMessages",
        dataType: "text",
        success: function (result) {
            if (!$.isEmptyObject(result) && result != "0") {
                $("#unreadMessages").html(" (" + result + ")");
                $("#accountMessages").append(" (" + result + ")");
            }
        },
        error: function (request, status, error) {

        }
    });

    $("#accountLogOut").click(function(){
        sessionStorage["username"] = null;
        sessionStorage["isAdminAccess"] = null;
        sessionStorage.clear();
        window.location.href = "/logout";
    });

    $("#vuforiaLogin").click(function(){
        sessionStorage["register"] = "false";
        $("#loginModal form")[0].reset();
        $("#loginModal").modal('show');
    });

    $('#pop-user-login').ajaxComplete(function( event, xhr, settings ) {
        if (xhr.responseText == "1"){
            sessionStorage["register"] = "false";
        }
    });

});

function activateAdminMenuTab(id, url)
{
    isAdminAccess = true;
    $("#" + id).show();
    $("#" +id + " a").attr("href",url);

}

function createAllAdminTabs()
{
    if (vuforiaUser.is_account_admin || vuforiaUser.is_account_read_only)
    {
        activateAdminMenuTab("account","/account_management");
    }
    if (vuforiaUser.is_account_admin || vuforiaUser.is_cloud_ops)
    {
        activateAdminMenuTab("apps",contextPath + "/adminAccount/accountsAppAdmin");
        activateAdminMenuTab("databases",contextPath + "/adminAccount/accountsDatabasesAdmin");
        activateAdminMenuTab("pricePlan",contextPath + "/adminAccount/pricePlansAdmin");
        activateAdminMenuTab("cloudRecoService",contextPath + "/adminAccount/admin/activeAccounts");
    }else if (vuforiaUser.is_account_read_only){
        activateAdminMenuTab("apps",contextPath + "/adminAccount/accountsAppAdmin");
        activateAdminMenuTab("databases",contextPath + "/adminAccount/accountsDatabasesAdmin");
    }
    if (vuforiaUser.is_billing_admin)
    {
        activateAdminMenuTab("billing", "/bill_run");
    }
    if (vuforiaUser.is_content_admin)
    {
        activateAdminMenuTab("contentManagement", "/content_management");
    }
    if (vuforiaUser.is_legal_admin)
    {
        activateAdminMenuTab("legal", "/admin_legal");
    }
    if (vuforiaUser.is_forum_admin)
    {
        activateAdminMenuTab("forums", "/forum_management");
    }
    if (vuforiaUser.is_reporting_admin)
    {
        activateAdminMenuTab("reports", "/report_management");
    }
    if (vuforiaUser.is_tms_settings_admin)
    {
        activateAdminMenuTab("settingsTab", contextPath + "/pricePlan/admin/cloudServicePricePlanSettings");
    }

    // highlight the admin tab in case user have admin roles
    sessionStorage["isAdminAccess"] = isAdminAccess;
    if (isAdminAccess)
    {
        displayVersionForAdmin();
        $(".adminMenu").show();
        $("#tspVersionDisplay").show();
        $("#drupalVersionDisplay").show();
    }
    else {
        $(".adminMenu").remove();
        $("#tspVersionDisplay").hide();
        $("#drupalVersionDisplay").hide();
    }
}

function initializeDrupalLinks()
{
    $("#billingStatements").attr("href", "/statement-history/" + vuforiaUser.eguid);
    $("#accountInfo").attr("href","/user/" + vuforiaUser.eguid);
    $("#paymentMethod").attr("href", "/user/" + vuforiaUser.eguid + "/billing_info");
}

function redirectToAdmin(){
    if (vuforiaUser.is_account_admin || vuforiaUser.is_account_read_only){
        window.location='/account_management';
    }else if (vuforiaUser.is_cloud_ops){
        window.location=contextPath + '/adminAccount/accountsAppAdmin';
    }else if (vuforiaUser.is_forum_admin){
        window.location='/forum_management';
    }else if (vuforiaUser.is_billing_admin){
        window.location='/bill_run';
    }else if (vuforiaUser.is_content_admin){
        window.location='/content_management';
    }else if (vuforiaUser.is_legal_admin){
        window.location='/admin_legal';
    }else if (vuforiaUser.is_reporting_admin){
        window.location='/report_management';
    }else if (vuforiaUser.is_tms_settings_admin){
        window.location=contextPath + '/pricePlan/admin/cloudServicePricePlanSettings';
    }


    if (vuforiaUser.is_account_admin || vuforiaUser.is_cloud_ops)
    {
        activateAdminMenuTab("apps",contextPath + "/adminAccount/accountsAppAdmin");
        activateAdminMenuTab("databases",contextPath + "/adminAccount/accountsDatabasesAdmin");
        activateAdminMenuTab("pricePlan",contextPath + "/adminAccount/pricePlansAdmin");
        activateAdminMenuTab("cloudRecoService",contextPath + "/adminAccount/admin/activeAccounts");
    }else if (vuforiaUser.is_account_read_only){
        activateAdminMenuTab("apps",contextPath + "/adminAccount/accountsAppAdmin");
        activateAdminMenuTab("databases",contextPath + "/adminAccount/accountsDatabasesAdmin");
    }
}

function getStringOfFixedLength(name,length){
    var nameLength;
    var modifiedName = '';
    if(name != null){
        nameLength = name.length;
        if(nameLength>length){
            modifiedName = name.substring(0,length);
            modifiedName = modifiedName+'...';
        }
        else{
            modifiedName = name;
        }
    }
    return modifiedName;
}

function generateTMSDevelopTabs(){
    $("#licenseManagerUrl").attr("href", contextPath + "/licenseManager/licenseListing");
    $("#targetManagerUrl").attr("href", contextPath + "/project/checkDeviceProjectsCreated?dataRequestedForUserId=");
    $("#develop .targetManager").attr("href", contextPath + "/licenseManager/licenseListing");
}

function generateDrupalDevelopTabs() {
    $("#licenseManagerUrl").attr("href", "/license-manager");
    $("#targetManagerUrl").attr("href", "/target-manager");
    $("#develop .targetManager").attr("href", "/license-manager");
}