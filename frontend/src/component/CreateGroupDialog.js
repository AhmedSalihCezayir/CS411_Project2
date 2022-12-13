import React, { useState, useEffect } from 'react';
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import AddIcon from '@mui/icons-material/Add';
import TextField from '@mui/material/TextField';
import ListItemButton from '@mui/material/ListItemButton';
import IconButton from '@mui/material/IconButton';
import Button from '@mui/material/Button';
import { DialogActions } from '@mui/material';
import axios from 'axios';

const CreateGroupDialog = (props) => {
	const [selected, setSelected] = useState([]);
	const [groupName, setGroupName] = useState('');
	const [friendsList, setFriendsList] = useState([]);
	const { onClose, open, handleAlert, currentUser } = {
		onClose: props.onClose,
		open: props.open,
		handleAlert: props.handleAlert,
		currentUser: props.currentUser,
	};

	useEffect(() => {
		async function fetchFriends() {
			const friendsList = await axios.get(
				`http://localhost:8080/friend/findAll/${currentUser}`
			);
			const friendsListNameArray = friendsList.data.data.map(
				(el) => el.name
			);
			setFriendsList(friendsListNameArray);
		}

		fetchFriends();
	}, [props.open]);

	const handleClose = () => {
		onClose();
	};

	const handleToggle = (index) => () => {
		let newSelected = [...selected];
		const i = newSelected.indexOf(index);
		if (i > -1) {
			newSelected.splice(i, 1);
		} else {
			newSelected.push(index);
		}

		setSelected(newSelected);
	};

	const handleCreateGroup = async () => {
		const selectedFriends = selected.map((i) => {
			return {
				name: friendsList[i],
			};
		});

		const selectedFriendsName = selected.map((i) => {
			return friendsList[i];
		});

		selectedFriends.push({ name: currentUser });

		const result = await axios.post(
			`http://localhost:8080/groupChat/createAll/${groupName}`,
			selectedFriends
		);

		const success =
			result.data.message === 'Creating Group Chat was successful.';

		let message;
		let color;

		if (success) {
			message = 'You successfully created a group with name ' + groupName;
			color = 'success';
		} else {
			message = 'There was a problem. Please try again';
			color = 'error';
		}

		handleAlert(
			{
				message,
				color,
			},
			selectedFriendsName
		);
		setGroupName('');
		setSelected([]);
		onClose();
	};

	const handleGroupName = (event) => {
		const { value } = event.target;
		setGroupName(value);
	};

	return (
		<Dialog onClose={handleClose} open={open}>
			<DialogTitle sx={{ pa: 0 }}>Add people to group</DialogTitle>
			<TextField
				onChange={handleGroupName}
				sx={{ m: 1 }}
				variant='outlined'
				label='Group Name'
			/>
			<List
				sx={{
					width: '100%',
					maxWidth: 360,
					bgcolor: 'background.paper',
				}}
			>
				{friendsList.map((value, index) => {
					const labelId = `checkbox-list-label-${value}`;

					return (
						<ListItem
							key={value}
							secondaryAction={
								<IconButton edge='end' aria-label='comments'>
									{!selected.includes(index) ? (
										<AddIcon />
									) : (
										<></>
									)}
								</IconButton>
							}
							sx={{
								backgroundColor: selected.includes(index)
									? '#6fbf73'
									: '',
							}}
							disablePadding
							divider
							onClick={handleToggle(index)}
						>
							<ListItemButton role={undefined} dense>
								<ListItemText id={labelId} primary={value} />
							</ListItemButton>
						</ListItem>
					);
				})}
			</List>

			<DialogActions>
				<Button onClick={() => handleCreateGroup()}>Create</Button>
			</DialogActions>
		</Dialog>
	);
};

export default CreateGroupDialog;
