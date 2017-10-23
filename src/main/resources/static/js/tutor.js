var User = function() {
    var self = this;
    self.password = ko.observable().extend({ required: true, minLength: 6, passwordPattern: true });
    self.repeatpassword = ko.observable().extend({ required: true, passwordValidation: [self.password], passwordPattern: true});
    self.birthDate = ko.observable().extend({ required: true, pastDate: true });
    self.address = ko.observable().extend({ required: true });
    self.phoneNumber = ko.observable().extend({ required: true });
    self.email = ko.observable().extend({ required: true, emailPattern: true });
    self.lastName = ko.observable().extend({ required: true });
    self.firstName = ko.observable().extend({ required: true });
    self.secondName = ko.observable().extend({ required: true });
    self.education = ko.observable().extend({ required: true });
    self.firstExperience = ko.observable();
    self.vkLink = ko.observable();
    self.skypeLink = ko.observable();
    self.price = ko.observable().extend({ required: true });
    self.comment = ko.observable();
    self.status = ko.observable();

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