ko.bindingHandlers.datepicker = {
    init: function (element, valueAccessor, allBindingsAccessor) {

        var options = {
            format: "dd.mm.yyyy", startDate: "01.01.1900",
            autoclose: true, incompleteDate: false, keyboardNavigation: false, forceParse: false, todayHighlight: true
        };

        if (allBindingsAccessor().datepickerOptions) {
            $.extend(options, allBindingsAccessor().datepickerOptions);
        }

        $(element).datepicker(options);

        ko.utils.registerEventHandler(element, "change", function (event) {
            var value = valueAccessor();
            if (ko.isObservable(value)) {
                var startCaretPos = $(element).getCursorPosition('selectionStart');
                value($(element).val());
                $(element).setCursorPosition(startCaretPos);
            }
        });

        ko.utils.registerEventHandler(element, "clearDate", function (event, param) {
            if (event.keyCode == 8 || event.keyCode == 46 || param == "inputmask") { // clear date only if backspace/delete pressed or event triggered by inputmask
                var value = valueAccessor();
                if (ko.isObservable(value)) {
                    value("");
                }
            }
        });

    },
    update: function (element, valueAccessor, allBindingsAccessor) {
        var value = valueAccessor();
        $(element).val(ko.utils.unwrapObservable(valueAccessor()));
    }
};
ko.validation.makeBindingHandlerValidatable('datepicker');

$.fn.getCursorPosition = function (position) {
    var el = $(this).get(0);
    var pos = 0;
    if (position in el) {
        if (position == 'selectionStart') {
            pos = el.selectionStart;
        } else if (position == 'selectionEnd') {
            pos = el.selectionEnd;
        }
    }
    return pos;
}

$.fn.setCursorPosition = function (pos) {
    this.each(function (index, elem) {
        if (elem.setSelectionRange) {
            elem.setSelectionRange(pos, pos);
        }
    });
    return this;
};
