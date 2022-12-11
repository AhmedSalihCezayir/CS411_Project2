import React, { useState } from 'react';
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog';
import Avatar from '@mui/material/Avatar';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ListItemText from '@mui/material/ListItemText';
import AddIcon from '@mui/icons-material/Add';
import InputAdornment from '@mui/material/InputAdornment';
import TextField from '@mui/material/TextField';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';

const AddFriendsDialog = (props) => {
	const { onClose, open, handleAlert, currentUser } = {
		onClose: props.onClose,
		open: props.open,
		handleAlert: props.handleAlert,
		currentUser: props.currentUser,
	};

	const [username, setUsername] = useState('');

	const handleClose = () => {
		setUsername('');
		onClose();
	};

	const handleFriendName = (event) => {
		const { value } = event.target;
		setUsername(value);
	};

	const handleAddFriend = () => {
		let success;
		axios
			.post(
				`https://localhost:8080/friend/add/${currentUser}/${username}`
			)
			.then((res) => {
				console.log(res.data);
				success = true;
				// success = res.data;
			});

		let message;
		let color;

		if (success) {
			message = 'You successfully added ' + username;
			color = 'success';
		} else {
			message = 'There was a problem. Please try again';
			color = 'error';
		}

		handleAlert({
			message,
			color,
		});
		setUsername('');
		onClose();
	};

	return (
		<Dialog onClose={handleClose} open={open}>
			<DialogTitle>Find Friends</DialogTitle>
			<TextField
				onChange={handleFriendName}
				sx={{ mx: 1 }}
				variant='outlined'
				label='Friend Username'
				InputProps={{
					startAdornment: (
						<InputAdornment position='start'>
							<SearchIcon />
						</InputAdornment>
					),
				}}
			/>
			<List>
				<ListItem autoFocus button onClick={() => handleAddFriend()}>
					<ListItemAvatar>
						<Avatar>
							<AddIcon />
						</Avatar>
					</ListItemAvatar>
					<ListItemText primary='Add friend' />
				</ListItem>
			</List>
		</Dialog>
	);
};

export default AddFriendsDialog;
