
var User = function (order, orderDtos) {
    var self = this;
    self.periodicities = [1,2,3 ,4,5, 6,7 ,8];
    self.orderDtos = ko.observable(orderDtos);
    self.message = ko.observable(order.message).extend({required: true});
    self.tutor = ko.observable(order.tutor);
    self.client = ko.observable(order.client);
    self.orderLessons = ko.observableArray();
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

User.prototype.selectDuration = function(tutorSubjectIndex, durationIndex) {
    var x = this.orderDtos()[tutorSubjectIndex].tutorSubjectDurations[durationIndex];
    var orderLesson = new OrderLesson(this.orderDtos()[tutorSubjectIndex].subject, x.duration);
    this.orderLessons.push((orderLesson));
};

User.prototype.addPeriodicity = function(subjectId, periodicity) {
    for(var i=0;i<this.orderLessons().length;i++) {
        if(this.orderLessons()[i].subject.id == subjectId) {
            this.orderLessons()[i].periodicity(periodicity);
        }
    }
};

var OrderLesson = function(subject, duration) {
    this.subject = subject;
    this.duration = duration;
    this.periodicity = ko.observable();
};
