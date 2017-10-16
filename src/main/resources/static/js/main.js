$(function () {

    var registerType = $('#getAnswer').attr("value"),
        registerEntryId = $('#answerId').attr("value");

    checkFlag(registerType, registerEntryId);

    $("#pageSizeSelect").on("change", function (e) {
        e.preventDefault();
        changePage("1", $(this).val());
    });
    $('#pageNumberSelect').on("change", function (e) {
        e.preventDefault();
        changePage($(this).val());
    });
    $('#searchButton').click(function (e) {
        e.preventDefault();
        $("#pageNumber").val("1");
        $("#searchForm").submit();
    });
    $(".sortable").on("click", function (e) {
        e.preventDefault();
        var sortField = $(this).attr("data-sort-field"),
            oldSortField = $(this).attr("data-old-sort-field"),
            sortDir = $(this).attr("data-sort-direction"),
            defaultSortDir = $(this).attr("default-sort-dir");
        sort(sortField, oldSortField, sortDir, defaultSortDir);
    });
    $(".pageable").on("click", function (e) {
        e.preventDefault();
        changePage($(this).attr("data-page-num"));
    });

    $(".printReport").click(function (e) {
        e.preventDefault();
        $("#errorStartDate").removeClass("error-validation");
        $("#errorEndDate").removeClass("error-validation");
        $("#dateStartInput").val("");
        $("#dateEndInput").val("");
        $("div .showDateType").empty();
        var $dialog = $("#reportDialog");
        var reportUrl = $(this).attr("data-url");
        var date = $(this).attr("data-type");
        var $form = $("#reportDialog .main form");
        var $div = $("<div></div>").addClass("showDateType");
        var $span = $("<p></p>").text("Тип даты: " + date);
        $div.append($span).append($("<br />"));
        $form.prepend($div);
        $(".overlay, #reportDialog .top .close, #reportDialog button.cancel").click(function (e) {
            e.preventDefault();
            $("body").removeClass("body-popup");
            $dialog.hide();
        });
        $dialog.find("form").on("submit", function (event) {
            if (validateReportDialog()) {
                $(this).attr("action", reportUrl);
            } else {
                event.preventDefault();
            }
        });
        $("body").addClass("body-popup");
        $dialog.show();
    });

    if ($.fn.selectric) {
        $("select").selectric();
    }

    if ($.fn.datepicker) {
        $(".datepicker").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: "dd.mm.yy"
        });
        $(".datepicker").parent().find(".cal-link").on("click", function(e) {
            e.preventDefault();
            $(this).parent().find(".datepicker").datepicker("show");
        })
    }

    if ($.fn.floatThead) {
        $("table").floatThead();
    }
});

function validateReportDialog() {
    $("#errorStartDate").removeClass("error-validation");
    $("#errorEndDate").removeClass("error-validation");
    var start;
    var end;
    var startMass;
    var endMass;
    var result = true;
    var today = new Date();
    var dateStart = $("#dateStartInput").val();
    var dateEnd = $("#dateEndInput").val();
    if (dateStart == "") {
        result = false;
        $("#messageStartDate").text("Поле не может быть пустым");
        $("#errorStartDate").addClass("error-validation");
    } else {
        startMass = dateStart.split(".");
        if (startMass.length != 3) {
            result = false;
            $("#messageStartDate").text("Неверный формат даты");
            $("#errorStartDate").addClass("error-validation");
        } else {
            start = new Date(startMass[2], startMass[1] - 1, startMass[0]);
            if (start.getTime() > today.getTime()) {
                result = false;
                $("#messageStartDate").text("Дата должна быть меньше текущей");
                $("#errorStartDate").addClass("error-validation");
            }
        }
    }
    if (dateEnd == "") {
        result = false;
        $("#messageEndDate").text("Поле не может быть пустым");
        $("#errorEndDate").addClass("error-validation");
    } else {
        endMass = dateEnd.split(".");
        if (endMass.length != 3) {
            result = false;
            $("#messageEndDate").text("Неверный формат даты");
            $("#errorEndDate").addClass("error-validation");
        } else {
            end = new Date(endMass[2], endMass[1] - 1, endMass[0]);
            if (end.getTime() > today.getTime()) {
                result = false;
                $("#messageEndDate").text("Дата должна быть меньше текущей");
                $("#errorEndDate").addClass("error-validation");
            }
        }
    }
    if ((dateStart != "" && dateEnd != "") && (startMass.length == 3 && endMass.length == 3) && end.getTime() < start.getTime()) {
        result = false;
        $("#messageEndDate").text("Дата начала должна быть меньше чем дата окончания");
        $("#errorEndDate").addClass("error-validation");
    }
    return result;
}

function changePage(pageNumber, pageSize) {
    var location = window.location.href;
    if (pageSize) {
        location = updateQueryParam(location, "pageSize", pageSize);
    }
    location = updateQueryParam(location, "pageNumber", pageNumber);
    window.location.href = location;
}

function sort(sortField, oldSortField, sortDir, defaultSortDir) {
    var location = window.location.href;
    location = updateQueryParam(location, "pageNumber", "1");
    if (sortField == oldSortField) {

        if (sortDir == null) {
            sortDir = defaultSortDir;
        } else if (sortDir == "desc") {
            sortDir = "asc";
        } else {
            sortDir = "desc"
        }

    } else {
        if (sortField == "header.createdDate" && oldSortField != null) {
            sortDir = "desc";
        } else {
            sortDir = defaultSortDir;
        }
    }
    location = updateQueryParam(location, "sortField", sortField);
    location = updateQueryParam(location, "sortOrder", sortDir);
    window.location.href = location;
}

function checkFlag(registerType, registerEntryId) {
    if (registerType == "request") {
    var result = $('#result').attr("value");
        window.location.href = '/registry/' + registerType + '/' + registerEntryId + '/' + result + '/print';
    }
    else if (registerEntryId) {
        window.location.href = '/registry/' + registerType + '/' + registerEntryId + '/print';
    }
}

function updateQueryParam(uri, key, value) {
    var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
    var separator = uri.indexOf('?') !== -1 ? "&" : "?";
    if (uri.match(re)) {
        return uri.replace(re, '$1' + key + "=" + value + '$2');
    }
    else {
        return uri + separator + key + "=" + value;
    }
}

$( document ).ready(function() {
    if ($.fn.floatThead) {
        $("table").floatThead('reflow');
    }
});