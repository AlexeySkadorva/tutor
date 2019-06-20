const CurrentUser = React.createClass({
    render: function () {
        return (
            <span>Hi, {this.props.current.username}</span>
        );
    }
});

const App = React.createClass({

    loadCurrentFromServer: function () {
        const self = this;
        $.ajax({
            url: "/api/users/current"
        }).then(function (data) {
            self.setState({current: data});
        });
    },

    getInitialState: function () {
        return {current: {}};
    },

    componentDidMount: function () {
        this.loadCurrentFromServer();
    },

    render() {
        return ( <CurrentUser current={this.state.current}/> );
    }
});

ReactDOM.render(<App />, document.getElementById('current'));