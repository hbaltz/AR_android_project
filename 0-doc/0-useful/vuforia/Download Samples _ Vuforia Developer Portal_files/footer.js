/*
 * ========================================================================================
 *   Copyright (c) 2012-2015 Qualcomm Connected Experiences, Inc. All Rights Reserved.
 *
 *   Confidential and Proprietary - Qualcomm Connected Experiences, Inc.
 *   Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States and
 *   other countries. Trademarks of QUALCOMM Incorporated are used with permission.
 * =========================================================================================
 */

function displayVersionForAdmin(){
    $.ajax({
        type: "GET",
        url: contextPath + "/vuforiaUtil/getVuforiaVersion",
        dataType: "text",
        success: function (result) {
            sessionStorage["TSPVersion"] = result;
            $("#tspVersionDisplay").text("TSP Version " + result);
        }
    });
}

$(document).ready(function(){

   $("#vuforiaCopyRightYear").html(new Date().getFullYear());

});

