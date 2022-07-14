import * as React from "react";
import Button from "@mui/material/Button";

class StartStopButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isStarted: false,
    };
    this.onStart = () => {
      this.setState({ isStarted: true });
      this.props.onStart();
    };
    this.onStop = () => {
      this.setState({ isStarted: false });
      this.props.onStop();
    };
  }

  render() {
    if (!this.state.isStarted) {
      return (
        <Button
          variant="contained"
          onClick={() => {
            this.onStart();
          }}
        >
          Start
        </Button>
      );
    } else {
      return (
        <Button
          variant="contained"
          onClick={() => {
            this.onStop();
          }}
        >
          Stop
        </Button>
      );
    }
  }
}

export default StartStopButton;
