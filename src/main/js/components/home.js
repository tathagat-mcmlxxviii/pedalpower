import * as React from "react";
import {
	startTrip,
	userEmail,
	sendDataPoint,
	endTrip,
	getUser,
} from "./../api/api.js";

import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

import CircularWithValueLabel from "./../components/trackbar.js";
import PointsSummary from "./../components/pointsSummary.js";
import StartStopButton from "./../components/startStopButton.js";

let dataPointTimer;

class HomeSection extends React.Component {
	constructor(props) {
		super(props);
		this.state = props.state;
	}

	render() {
		return (
			<Box sx={{ flexGrow: 1 }}>
				<Grid container spacing={2}>
					<Grid item xs={4}></Grid>
					<Grid item xs={4}>
						<Box
							sx={{
								display: "flex",
								justifyContent: "center",
							}}
						>
							<PointsSummary points={this.state.points} />
						</Box>
					</Grid>
					<Grid item xs={4}></Grid>
					<Grid item xs={5}></Grid>
					<Grid item xs={2}>
						<Box
							sx={{
								px: "32%",
							}}
						>
							<CircularWithValueLabel value={this.state.distance} />
						</Box>
					</Grid>
					<Grid item xs={5}></Grid>
					<Grid item xs={4}></Grid>
					<Grid item xs={4}>
						<Box
							sx={{
								display: "flex",
								justifyContent: "center",
							}}
						>
							<StartStopButton
								onStart={this.handleStart.bind(this)}
								onStop={this.handleStop.bind(this)}
							/>
						</Box>
					</Grid>
					<Grid item xs={4}></Grid>
				</Grid>
			</Box>
		)
	}

	handleStart() {
		startTrip(userEmail).then((trip) => {
			this.updateState(trip.km, this.state.points);

			this.startUpdateLoop();
		});
	}

	startUpdateLoop() {
		dataPointTimer = setInterval(() => {
			const promise = sendDataPoint();
			if (promise) {
				promise.then((trip) => {
					this.updateState(trip.km, this.state.points);
				});
			}
		}, 2000);
	}

	stopUpdateLoop() {
		clearInterval(dataPointTimer);
	}

	handleStop() {
		this.stopUpdateLoop();
		endTrip(userEmail).then((user) => {
			this.updateState(this.state.distance, user.points);
		});
	}

	updateState(distance, points) {
		this.setState({
			distance: distance,
			points: points,
		});
	}
}

export default HomeSection;