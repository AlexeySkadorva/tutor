const makeTransplantation = "Проведена трансплантация";

var Patient = function(patient) {
    self.photo = ko.observable(patient.data.photo);
};


function add_photo(photo, patientId) {
    $(document.body).append('<input class="file-attach" id="_file_" type="file" name="file" accept="image/jpeg, image/png, image/gif, image/bmp"/>')
    inputfile = $('#_file_');
    inputfile.change(function () {
        var file = this.files[0];
        if(file == null) return;

        if (file.size > 4194304){
            $("#photo-size").modal("show");
            return;
        }
        if (!check_type(file.name)) {
            $("#attach-type").modal("show");
            return;
        }

        photo(null);

        var formData = new FormData();
        formData.append('file', file);
        ajax = $.ajax({
            url: "/patients/" + patientId + "/photo/upload",
            method: "POST",
            data: formData,
            async: false,
            success: function (data) {
                $("#patientPhoto").prop("src", "/content/download/" + data.id);
                photo(data);
                window.onbeforeunload = null;
            },
            cache: false,
            contentType: false,
            processData: false
        });
    });
    var top = $(window).scrollTop();
    inputfile.focus().trigger('click');
    $(window).scrollTop(top);
};