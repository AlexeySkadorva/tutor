const Message = React.createClass({
    render: function () {
        return (
            <div className="row">
                <h4 className="message-label">{this.props.message.user.username}
                    <small className="date">{GetFormattedDate(new Date(this.props.message.date))}</small>
                </h4>
                <p className="message col-lg-8 col-md-7 col-sm-6">{this.props.message.text}</p>
            </div>);
    }
});

const MessageTable = React.createClass({
    componentDidMount: function () {
        const self = this;
        const socket = new SockJS('/messages');
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/newMessage/1/2', function (response) {
                const newMessages = self.props.messages;
                newMessages.push(JSON.parse(response.body));
                self.setState({messages: newMessages})
            });
        });
    },

    render: function () {
        setTimeout(function () {
            document.getElementById('messages').scrollTop = document.getElementById('messages-list').offsetHeight;
        }, 100);

        const rows = [];
        this.props.messages.forEach(function (message) {
            rows.push(<Message message={message}/>);
        });
        return (
            <div id="messages-list" className="container-fluid">{rows}</div>
        );
    }
});

const App = React.createClass({
    loadUsersFromServer: function () {
        const self = this;
        $.ajax({
            url: "/api/messages"
        }).then(function (data) {
            self.setState({messages: data});
        });
    },

    getInitialState: function () {
        return {messages: []};
    },

    componentDidMount: function () {
        this.loadUsersFromServer();
    },

    render() {
        return ( <MessageTable messages={this.state.messages}/> );
    }
});

ReactDOM.render(<App />, document.getElementById('messages'));

/**
 * @return {string}
 */
function GetFormattedDate(date) {
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const year = date.getFullYear();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    return month + "/" + day + "/" + year + ' ' + hours + ':' + minutes + ':' + seconds;
}