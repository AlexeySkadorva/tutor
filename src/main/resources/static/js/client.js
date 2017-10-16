var User = function() {
    var self = this;
    self.login = ko.observable().extend({ required: true, minLength: 6, loginPattern: true });
    self.password = ko.observable().extend({ required: true, minLength: 6, passwordPattern: true });
    self.repeatpassword = ko.observable().extend({ required: true, passwordValidation: [self.password], passwordPattern: true});
    self.birthDate = ko.observable().extend({ required: true, pastDate: true });
    self.address = ko.observable().extend({ required: true });
    self.phoneNumber = ko.observable().extend({ required: true });
    self.email = ko.observable().extend({ required: true, emailPattern: true });
    self.lastName = ko.observable().extend({ required: true });
    self.firstName = ko.observable().extend({ required: true });
    self.secondName = ko.observable().extend({ required: true });
    self.gender = ko.observable().extend({ required: true });
    self.grade = ko.observable();

    self.course = ko.observable();
    self.clientType = ko.observable(0).extend({ required: true });

    self.isGradeDisabled = ko.observable(true);
    self.isCourseDisabled = ko.observable(true);
};

User.prototype.save = function() {
    var self = this;
    self.errors = ko.validation.group(self);
    if (self.errors().length === 0) {
        return true;
    }else{
        self.errors.showAllMessages();
    }
};

User.prototype.changeClientType = function(clientType) {
    var self = this;
    if(clientType == 2) {
        self.isGradeDisabled(false);
        self.isCourseDisabled(true);
    }
    if(clientType == 3) {
        self.isCourseDisabled(false);
        self.isGradeDisabled(true);
    }
    if(clientType == 1 || clientType == 4) {
        self.isCourseDisabled(true);
        self.isGradeDisabled(true);
    }
    self.grade(null);
    self.course(null);
};