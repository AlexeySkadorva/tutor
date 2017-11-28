var User = function (client) {
    var self = this;
    self.password = ko.observable(client.user.password).extend({required: true, minLength: 6, passwordPattern: true});
    self.repeatpassword = ko.observable(client.user.password).extend({
        required: true,
        passwordValidation: [self.password],
        passwordPattern: true
    });
    self.birthDate = ko.observable(client.user.birthDate).extend({required: true, pastDate: true});
    self.address = ko.observable(client.user.address).extend({required: true});
    self.phoneNumber = ko.observable(client.user.phoneNumber).extend({required: true});
    self.email = ko.observable(client.user.email).extend({required: true, emailPattern: true});
    self.lastName = ko.observable(client.user.lastName).extend({required: true});
    self.firstName = ko.observable(client.user.firstName).extend({required: true});
    self.secondName = ko.observable(client.user.secondName).extend({required: true});
    self.grade = ko.observable(client.grade);

    self.course = ko.observable(client.course);
    self.clientType = ko.observable(0).extend({required: true});

    self.isGradeDisabled = ko.observable(true);
    self.isCourseDisabled = ko.observable(true);

    self.parentSecondName = ko.observable(client.clientParent.secondName);
    self.paretnFirstName = ko.observable(client.clientParent.firstName);
    self.parentLastName = ko.observable(client.clientParent.lastName);
    self.parentPhoneNumber = ko.observable(client.clientParent.phoneNumber);


    if (client.clientType != null && (client.clientType.id == 1 || client.clientType.id == 2)) {
        $("#parent-info").show();
    }

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

User.prototype.changeClientType = function (clientType) {
    if (clientType == 1 || clientType == 2) {
        $("#parent-info").show();
    } else {
        $("#parent-info").hide();
    }
    var self = this;
    if (clientType == 2) {
        self.isGradeDisabled(false);
        self.isCourseDisabled(true);
    }
    if (clientType == 3) {
        self.isCourseDisabled(false);
        self.isGradeDisabled(true);
    }
    if (clientType == 1 || clientType == 4) {
        self.isCourseDisabled(true);
        self.isGradeDisabled(true);
    }
    self.grade(null);
    self.course(null);
};