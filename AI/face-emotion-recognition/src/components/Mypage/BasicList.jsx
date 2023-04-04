import * as React from 'react';
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import InboxIcon from '@mui/icons-material/Inbox';
import DraftsIcon from '@mui/icons-material/Drafts';

import { useState, useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { tokenState } from '../../states/loginState';
import MyInfo from './MyInfo';
import MyPost from './MyPost';

export default function BasicList() {

  const API_BASE_URL = 'https://j8b301.p.ssafy.io/app';
  const token = useRecoilValue(tokenState);
  const [ state, setState ] = useState(1);
  
  // const loading = () => {
  //   axios.get('/board/my', {
  //     params: {
  //       page: 0
  //     }
  //   }, {
  //     headers: {
  //       'Authorization': token
  //     }
  //   }).then((res) => {
  //     console.log(res);
  //   })
  // }
  
  // useEffect(() => {

  // })

  return (
    <Box sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
      <nav aria-label="main mailbox folders">
        <List>
          <ListItem disablePadding onClick={() => setState(1)}>
            <ListItemButton>
              <ListItemIcon>
                <InboxIcon />
              </ListItemIcon>
              <ListItemText primary="내정보" />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding onClick={() => setState(2)}>
            <ListItemButton>
              <ListItemIcon>
                <DraftsIcon />
              </ListItemIcon>
              <ListItemText primary="내가쓴글" />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding onClick={() => setState(3)}>
            <ListItemButton>
              <ListItemIcon>
                <DraftsIcon />
              </ListItemIcon>
              <ListItemText primary="북마크" />
            </ListItemButton>
          </ListItem>
        </List>
      </nav>
      {state === 1 ? <MyInfo /> : state == 2 ? <MyPost /> : <div>something</div>}
      <Divider />
    </Box>
  );
}