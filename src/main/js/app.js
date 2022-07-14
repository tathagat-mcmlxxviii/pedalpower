'use strict';

// tag::vars[]
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
// end::vars[]
require('./api/api.js')

import Button from '@mui/material/Button';
import SimpleBottomNavigation from './components/bottomnavigation.js';
import ResponsiveAppBar from './components/appbar.js';
import CircularWithValueLabel from './components/trackbar.js';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { startTrip } from './api/api.js';


const userEmail = "test@mail.com"

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = { distance : 0 };
	}

	componentDidMount() { // <2>
		setInterval(() => {
			fetch("http://localhost:8080/trip/current?email=" + userEmail)
			.then(data => {
				const obj = JSON.parse(data)
				if(obj && this.state.distance < 100){
					this.setState({
						distance: this.state.distance + 10
					})
				}
			})
		}, 10000)		
	}

	render() { // <3>
		return (
			<React.Fragment>
				<ResponsiveAppBar/>
				<div style={{height: '100%'}}>
					<Box sx={{ flexGrow: 1 }}>
						<Grid container spacing={2} justifyContent="center">
							<Grid item xs={12}></Grid>
							<Grid item xs={5}></Grid>
							<Grid item xs={2} justifyContent="center">
									<CircularWithValueLabel value={this.state.distance}/>
							</Grid>
							<Grid item xs={5} ></Grid>
							<Grid item xs={4} ></Grid>
							<Grid item xs={4} justifyContent="center">
									<Button variant="contained" onClick={() => {this.handleStart()}}>Hello World</Button>
							</Grid>
							<Grid item xs={4} ></Grid>
						</Grid>
					</Box>
				</div>
				<Box sx={{   
					flexGrow: 1,  
					position: 'absolute',
					display: 'flex',     
					alignItems: 'center',
					justifyContent: 'center'}
					}>
					<SimpleBottomNavigation/>
				</Box>
			</React.Fragment>
		)
	}

	handleStart(){
		startTrip(userEmail).then(trip => {
			this.setState({distance : trip.km})
		})
	}
}
// end::app[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]

