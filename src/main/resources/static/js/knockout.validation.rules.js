ko.validation.init({
    grouping: {
        deep: true,
        live: true,
        observable: true
    },
    registerExtenders: true,
    messagesOnModified: true,
    insertMessages: true,
    parseInputAttributes: true,
    messageTemplate: null,
    decorateElement: true,
    errorElementClass: 'has-error'
}, true);

const validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png", ".docx", ".pdf"];
const maxFileSize = 52428800;
const maxFileSizeArray = 5;
const maxFileSizeName = 255;
const conclusionFitsWithLimits = 4;
const DUMMY_PASSWORD = "DUMMY_PASSWORD";

var passportSeriesMask = /^[A-Z]{2}[0-9]{7}$/i;
var passportNumberMask = /^[0-9]{7}[A-Z][0-9]{3}[A-Z]{2}[0-9]$/i;
var passwordMask = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*?_~-])[A-Za-z\d!@#$%^&*?_~-]{1,}/i;
var emailMask = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
var loginMask = /^[A-Za-z0-9]{6,30}$/i;

ko.validation.rules["passwordValidation"] = {
    validator: function (password, params, callback) {
        var repeatPassword = params[0]();
        return password == repeatPassword;
    },
    message: "Пароли долны быть одинаковые"
};

ko.validation.rules["passwordPattern"] = {
    validator: function (value, params) {
        return DUMMY_PASSWORD == value || passwordMask.test(value);
    },
    message: "Пароль должен содержать латинские буквы в обоих регистрах, цифры и спецсимволы (!@#$%^&*?_~-)"
};


ko.validation.rules["emailPattern"] = {
    validator: function (value, params) {
        return emailMask.test(value);
    },
    message: "E-mail должен содержать буквы, символ @ и точку. Пример: example@gmail.com"
};

ko.validation.rules["required"] = {
    validator: function (a, b) {
        if (ko.isObservable(a)) {
            if (a() == null) return false;
            else return true;
        }
        var c;
        return void 0 === a || null === a ? !b : (c = a, "string" == typeof a && (c = String.prototype.trim ? a.trim() : a.replace(/^\s+|\s+$/g, "")), b ? (c + "").length > 0 : !0)
    },
    message: "Поле обязательно для заполнения"
};

ko.validation.rules["minLength"] = {
    validator: function (value, param) {
        if (!value) {
            return true
        }
        return value.toString().length >= param;
    },
    message: "Длина поля должна быть больше " + 1 + " символов"
};

ko.validation.rules["loginPattern"] = {
    validator: function (value, params) {
        return loginMask.test(value);
    },
    message: "Логин должен содержать латинские буквы или цифры"
};

ko.validation.rules["pastDate"] = {
    validator: function (date, param) {
        if (!date) {
            return true;
        }
        var currentDate = new Date();
        var inputDate = parseDate(date);
        return inputDate.isBefore(currentDate);
    },
    message: "Дата не может быть больше текущей"
};

function parseDate(date) {
    return moment(date, ["DD.MM.YYYY", "MM.YYYY", "YYYY"], true);
}

ko.validation.registerExtenders();
