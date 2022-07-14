"use strict";

// tag::vars[]
const React = require("react"); // <1>
const ReactDOM = require("react-dom"); // <2>
// end::vars[]
import {
  startTrip,
  userEmail,
  sendDataPoint,
  endTrip,
  getUser,
} from "./api/api.js";

import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";

import SimpleBottomNavigation from "./components/bottomnavigation.js";
import ResponsiveAppBar from "./components/appbar.js";
import CircularWithValueLabel from "./components/trackbar.js";
import PointsSummary from "./components/pointsSummary.js";
import StartStopButton from "./components/startStopButton.js";

let dataPointTimer;

// tag::app[]
class App extends React.Component {
  // <1>

  constructor(props) {
    super(props);
    this.state = {
      distance: 0,
      points: 0,
    };
  }

  componentDidMount() {
    // <2>
    getUser(userEmail).then((user) => {
      this.updateState(this.state.distance, user.points);
    });
  }

  render() {
    // <3>
    return (
      <React.Fragment>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            minHeight: "100vh",
          }}
        >
          <ResponsiveAppBar />
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
          <Box
            component="footer"
            sx={{
              py: 2,
              px: 2,
              mt: "auto",
              backgroundColor: (theme) => theme.palette.grey[200],
            }}
          >
            <Container maxWidth="sm">
              <SimpleBottomNavigation />
            </Container>
          </Box>
        </Box>
      </React.Fragment>
    );
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
// end::app[]

// tag::render[]
ReactDOM.render(<App />, document.getElementById("react"));
// end::render[]
