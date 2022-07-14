'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
// end::vars[]

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {cities: []};
	}

	componentDidMount() { // <2>
		fetch('/staticdata/cities')
		   .then(response => response.json())
		   .then(data => {
				console.log(data);
				this.setState({cities: data});
			});
	}

	render() { // <3>
		return (
			<CityList cities={this.state.cities}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class CityList extends React.Component{
	render() {
		const cities = this.props.cities.map(city =>
			<City key={city.id} city={city}/>
		);
		return (
			<table>
				<tbody>
					{cities}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class City extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.city.name}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]