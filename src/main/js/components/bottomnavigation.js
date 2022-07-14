import * as React from 'react';
import Box from '@mui/material/Box';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import HomeIcon from '@mui/icons-material/Home';
import DirectionsBikeIcon from '@mui/icons-material/DirectionsBike';
import HistoryIcon from '@mui/icons-material/History';
import TrainIcon from '@mui/icons-material/Train';

export default function SimpleBottomNavigation() {
  const [value, setValue] = React.useState(0);

  return (
    <Box sx={{ width: 500 }}>
      <BottomNavigation
        showLabels
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
      >
        <BottomNavigationAction label="Home" icon={<HomeIcon />} />
        <BottomNavigationAction label="Ranking" icon={<DirectionsBikeIcon />} />
        <BottomNavigationAction label="History" icon={<HistoryIcon />} />
        <BottomNavigationAction label="Tickets" icon={<TrainIcon />} />
      </BottomNavigation>
    </Box>
  );
}
