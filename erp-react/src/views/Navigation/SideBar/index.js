import { Box, Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, SwipeableDrawer } from '@mui/material'
import MenuIcon from '@mui/icons-material/Menu';
import MailIcon from '@mui/icons-material/Mail';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import SwitchAccountIcon from '@mui/icons-material/SwitchAccount';
import LocalAtmIcon from '@mui/icons-material/LocalAtm';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import FactoryIcon from '@mui/icons-material/Factory';
import React from 'react'

export default function SideBar() {

  // 왼쪽 사이트바 메뉴
  const [leftSwit, setLeftSwit] = React.useState({ swit: false });
  const [iconList, setIconList] = React.useState([<SwitchAccountIcon />, <LocalAtmIcon />, <LocalShippingIcon />, <FactoryIcon />]);

  // 왼쪽 사이드바 키 이벤트
  const toggleDrawer = (anchor, open) => (event) => {
    if (
      event &&
      event.type === 'keydown' &&
      (event.key === 'Tab' || event.key === 'Shift')
    ) {
      return;
    }

    setLeftSwit({ ...leftSwit, [anchor]: open });
  };
  // 왼쪽 사이드바 HTML
  const list = (anchor) => (
    <Box
    sx={{ width: 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {['Profile', 'Email'].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <Divider />
      <List>
        {['인사', '회계', '물류', '제조'].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton>
              <ListItemIcon>
                {iconList[index]}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </Box>
  );

  return (
    <>
        <IconButton
            onClick={toggleDrawer('left', true)}
            size="large"
            edge="start"
            color="inherit"
            aria-label="open drawer"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <SwipeableDrawer
            anchor={'left'}
            open={leftSwit['left']}
            onClose={toggleDrawer('left', false)}
            onOpen={toggleDrawer('left', true)}
          >
            {list('left')}
          </SwipeableDrawer>
    </>
  )
}
