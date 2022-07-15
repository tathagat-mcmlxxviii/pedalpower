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

import HomeSection from "./components/home.js";

// tag::app[]
class App extends React.Component {
  // <1>

  constructor(props) {
    super(props);
    this.state = {
      distance: 0,
      points: 0
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
          
          <HomeSection state={this.state}/>
          
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
