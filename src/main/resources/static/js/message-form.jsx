const MessageFrom = React.createClass({
    addMessage() {
        $.ajax({
            url: '/api/messages',
            type: 'PUT',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "message": $('#message-text').val(),
                "userFrom" : 1,
                "userTo" : 2
            }),
            success: function (result) {
                $('#message-text').val('')
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert('Can\'t add message')
            }
        });
    },
    render: function () {
        return (
            <div className="message-input col-lg-offset-2">
                <div className="form-group col-lg-7">
                    <div className="col-lg-10 col-md-9 col-sm-8">
                        <textarea id="message-text" rows="3" className="form-control col-lg-6 col-md-5 col-sm-4" placeholder="Message..."/>
                    </div>
                    <div className="col-lg-2 col-md-3 col-sm-4">
                        <button className="btn btn-default" type="button" onClick={this.addMessage}>Send</button>
                    </div>

                </div>
            </div>);
    }
});

ReactDOM.render(<MessageFrom/>, document.getElementById('message-form'));