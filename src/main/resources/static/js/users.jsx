const User = React.createClass({
    render: function () {
        return (
            <tr>
                <td>{this.props.user.username}</td>
            </tr>);
    }
});
const UserTable = React.createClass({
    componentDidMount: function () {
        const self = this;
        const socket = new SockJS('/messages');
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/newUser', function (response) {
                const newUsers = self.props.users;
                newUsers.push(JSON.parse(response.body));
                self.setState({users: newUsers})
            });
            stompClient.subscribe('/topic/removeUser', function (response) {
                const newUsers = self.props.users;
                const user = JSON.parse(response.body);
                self.props.users.forEach(function (el) {
                    if (el.username == user.username) {
                        newUsers.splice(newUsers.indexOf(el), 1);
                    }
                });
                self.setState({users: newUsers});
            });
        });
    },

    render: function () {
        const rows = [];
        this.props.users.forEach(function (user) {
            rows.push(<User user={user}/>);
        });
        return (
            <table className="table">
                <thead>
                <tr>
                    <th>Online</th>
                </tr>
                </thead>
                <tbody>{rows}</tbody>
            </table>
        );
    }
});

const App = React.createClass({

    loadUsersFromServer: function () {
        const self = this;
        $.ajax({
            url: "/api/users"
        }).then(function (data) {
            self.setState({users: data});
        });
    },

    getInitialState: function () {
        return {users: []};
    },

    componentDidMount: function () {
        this.loadUsersFromServer();
    },

    render() {
        return ( <UserTable users={this.state.users}/> );
    }
});

ReactDOM.render(<App />, document.getElementById('online'));