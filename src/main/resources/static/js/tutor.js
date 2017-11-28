var User = function (tutor) {
    var self = this;
    self.password = ko.observable(tutor.user.password).extend({required: true, minLength: 6, passwordPattern: true});
    self.repeatpassword = ko.observable(tutor.user.password).extend({
        required: true,
        passwordValidation: [self.password],
        passwordPattern: true
    });
    self.birthDate = ko.observable(tutor.user.birthDate).extend({required: true, pastDate: true});
    self.address = ko.observable(tutor.user.address).extend({required: true});
    self.phoneNumber = ko.observable(tutor.user.phoneNumber).extend({required: true});
    self.email = ko.observable(tutor.user.email).extend({required: true, emailPattern: true});
    self.lastName = ko.observable(tutor.user.lastName).extend({required: true});
    self.firstName = ko.observable(tutor.user.firstName).extend({required: true});
    self.secondName = ko.observable(tutor.user.secondName).extend({required: true});
    self.education = ko.observable(tutor.education).extend({required: true});
    self.firstExperience = ko.observable(tutor.firstExperience);
    self.vkLink = ko.observable(tutor.vkLink);
    self.skypeLink = ko.observable(tutor.skypeLink);
    self.telegram = ko.observable(tutor.telegram);
    self.price = ko.observable(tutor.price).extend({required: true});
    self.comment = ko.observable(tutor.comment);
    self.status = ko.observable(tutor.status);
};

User.prototype.save = function () {
    var self = this;
    self.errors = ko.validation.group(self);
    if (self.errors().length === 0) {
        return true;
    } else {
        self.errors.showAllMessages();
    }
};