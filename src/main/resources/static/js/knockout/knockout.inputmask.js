const relationRussianAndEnglishKeys = {"й":"q","ц":"w","у":"e","к":"r","е":"t","н":"y","г":"u","ш":"i","щ":"o","з":"p","х":"","ъ":"",
            "ф":"a","ы":"s","в":"d","а":"f","п":"g","р":"h","о":"j","л":"k","д":"l","ж":"","э":"",
            "я":"z","ч":"x","с":"c","м":"v","и":"b","т":"n","ь":"m","б":"","ю":"","/":"",
            "Й":"Q","Ц":"W","У":"E","К":"R","Е":"T","Н":"Y","Г":"U","Ш":"I","Щ":"O","З":"P","Х":"","Ъ":"",
            "Ф":"A","Ы":"S","В":"D","А":"F","П":"G","Р":"H","О":"J","Л":"K","Д":"L","Ж":"","Э":"",
            "Я":"Z","Ч":"X","С":"C","М":"V","И":"B","Т":"N","Ь":"M","Б":"","Ю":"",",":""
};

const idOfBelarusInCountriesList = 61373;
const passportTypeId = 1;

var isPassportSeriesMasked = false;
var isPassportNumberMasked = false;

ko.bindingHandlers.inputmask = {
    init: function(element, valueAccessor, allBindingsAccessor) {
        var options = allBindingsAccessor().inputmaskOptions;

        if(options.type == "number") {
            $(element).inputmask({
                mask: "99{2,6}",
                definitions: {"9": {validator: "[0-9]"}},
                placeholder: ""
            });
        }

        if(options.type == "numberOrDash") {
            $(element).inputmask("Regex", {
                regex: "^[1-9][0-9]?$|^0$|^100$|^-$"
            });
        }

        if(options.type == "floatNumber") {
            $(element).inputmask({
                mask: "99{2,6}",
                definitions: {"9": {validator: "[0-9,.]"}},
                placeholder: ""
            });
        }

        if(options.type == "mfi") {
            $(element).inputmask({
                mask: "(9{1,8})|(9{1,8}*9{1,3})",
                definitions: {"9": {validator: "[0-9]"}, "*": {validator: "[.,]"}},
                placeholder: ""
            });
        }

        if (options.type == "date") {
            $(element).inputmask("dd.mm.yyyy", {
                placeholder: "дд.мм.гггг",
                clearIncomplete: true,
                yearrange: { minyear: 1900, maxyear: moment().year() },
                onBeforePaste: function (pastedValue, opts) {
                    var isValid = Inputmask.isValid(pastedValue, { alias: "dd.mm.yyyy"});
                    if (!isValid) {
                        $(element).trigger("clearDate", "inputmask");
                        return false;
                    }
                    return pastedValue;
                }
            });
        }

        if(options.type == "kpfCoefficient") {
            $(element).inputmask({
                mask: "(9*9{1,6})|(z9*9{1,6})",
                definitions: {"9": {validator: "[0-9]"}, "*": {validator: "[.,]"}, "z": {validator: "[-]"}},
                placeholder: ""
            });
        }

        if(checkUsingTranslation()){
            if(options.type == "passportSeries") {
                addPassportInputMask(element, "passportSeries");
                isPassportSeriesMasked = true;
            }

            if(options.type == "passportNumber") {
                addPassportInputMask(element, "passportNumber");
                isPassportNumberMasked = true;
            }
        }

        ko.utils.registerEventHandler(element, "change", function(event) {
            if(options.type == "passportSeries") {
                if(checkUsingTranslation() && !isPassportSeriesMasked){
                    addPassportInputMask(element, "passportSeries");
                    isPassportSeriesMasked = true;
                } else if(!checkUsingTranslation() && isPassportSeriesMasked){
                    $(element).inputmask("remove");
                    isPassportSeriesMasked = false;
                }
            }

            if(options.type == "passportNumber") {
                if(checkUsingTranslation() && !isPassportNumberMasked){
                    addPassportInputMask(element, "passportNumber");
                    isPassportNumberMasked = true;
                } else if(!checkUsingTranslation() && isPassportNumberMasked){
                    $(element).inputmask("remove");
                    isPassportNumberMasked = false;
                }
            }
        });
    }
};

function addPassportInputMask(element, type) {
    var inputMask;
    if(type == "passportNumber") {
        inputMask = "9999999A999AA9";
    } else if(type = "passportSeries") {
        inputMask = "AA9999999";
    }

    $(element).inputmask({
        mask: inputMask,
        definitions: {'A': {validator: "[а-яА-Яa-zA-Z]"}, '9': {validator: "[0-9]"}},
        placeholder: "",
        onBeforeMask: function (value, opts) {
            return transliterate(value).toUpperCase();
        },
        onBeforeWrite: function (event, buffer, caretPos, opts) {
            return transliterateChar(buffer, caretPos);
        }
    });

};

function checkUsingTranslation(){
  return ($("#country").val() == idOfBelarusInCountriesList && $("#document").val() == passportTypeId);
};

function transliterate(word){
    return word.split('').map(function (char) {
        return relationRussianAndEnglishKeys[char] || char;
    }).join("");
};

function transliterateChar(buffer, caretPos){
    var char = buffer[caretPos - 1];
    var result = relationRussianAndEnglishKeys[char] || char;
    buffer[caretPos - 1] = result;

    return {
        refreshFromBuffer: {
            start: caretPos - 1,
            end: caretPos
        },
        buffer: buffer
    };
};
