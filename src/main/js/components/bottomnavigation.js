import * as React from "react";
import Box from "@mui/material/Box";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import HomeIcon from "@mui/icons-material/Home";
import DirectionsBikeIcon from "@mui/icons-material/DirectionsBike";
import HistoryIcon from "@mui/icons-material/History";
import TrainIcon from "@mui/icons-material/Train";

export default function SimpleBottomNavigation() {
  const [value, setValue] = React.useState(0);

  return (
    <Box
      sx={{
        backgroundColor:(theme) => theme.palette.grey[200],
      }}
    >
      <BottomNavigation
        showLabels
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
      >
        <BottomNavigationAction
          label="Home"
          icon={<HomeIcon />}
          sx={{
            backgroundColor: (theme) => theme.palette.grey[200],
          }}
        />
        <BottomNavigationAction
          label="Ranking"
          icon={<DirectionsBikeIcon />}
          sx={{
            backgroundColor: (theme) => theme.palette.grey[200],
          }}
        />
        <BottomNavigationAction
          label="History"
          icon={<HistoryIcon />}
          sx={{
            backgroundColor: (theme) => theme.palette.grey[200],
          }}
        />
        <BottomNavigationAction
          label="Tickets"
          icon={<TrainIcon />}
          sx={{
            backgroundColor: (theme) => theme.palette.grey[200],
          }}
        />
      </BottomNavigation>
    </Box>
  );
}
