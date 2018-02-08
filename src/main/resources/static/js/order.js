
var User = function (order, orderDtos, lessonTypes) {
    var self = this;
    self.periodicities = [1,2,3 ,4,5, 6,7 ,8];
    self.lt = lessonTypes;
    self.orderDtos = ko.observable(orderDtos);
    self.message = ko.observable(order.message).extend({required: true});
    self.tutor = ko.observable(order.tutor);
    self.client = ko.observable(order.client);
    self.lessonTypes = ko.observable(order.tutor.lessonTypes);
    self.orderStatus = ko.observable(order.orderStatus);

    self.client = ko.observable(order.client);
    self.orderLessons = ko.observableArray().extend({required: true});
    for(var i=0;i<this.orderDtos().length;i++) {
        self.orderLessons.push(new OrderLesson(this.orderDtos()[i].subject, this.orderDtos()[0].tutorSubjectDurations[i].duration));
    }
};

User.prototype.save = function () {
    var self = this;
    self.errors = ko.validation.group(self);
    if (self.errors().length === 0) {
        self.saveRequest();
    } else {
        self.errors.showAllMessages();
    }
};

User.prototype.selectDuration = function(tutorSubjectIndex, durationIndex) {
    var subjectId = this.orderDtos()[tutorSubjectIndex].subject.id;
    for(var i=0;i<this.orderLessons().length;i++) {
        if(this.orderLessons()[i].subject.id == subjectId) {
            this.orderLessons()[i].duration(this.orderDtos()[tutorSubjectIndex].tutorSubjectDurations[durationIndex].duration);
        }
    }
};

var OrderLesson = function(subject, duration) {
    this.subject = subject;
    this.duration = ko.observable();
    this.periodicity = ko.observable();
    this.lessonType = ko.observable();
};
User.prototype.saveRequest = function(){
    $.ajax("/orders", {
        data: this._serialize(),
        contentType: "application/json",
        async: false,
        type: "POST"
    }).success(function (){
        window.location.replace("/order/send");
    });
};
User.prototype._serialize = function() {
    var self = this;

    return ko.toJSON({
        "client": self.client(),
        "tutor": self.tutor(),
        "orderStatus.id": self.orderStatus().id,
        "message": self.message(),
        "orderLessons": self.orderLessons()
    });
};