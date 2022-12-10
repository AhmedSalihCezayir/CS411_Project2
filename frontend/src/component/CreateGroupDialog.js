import React, { useState } from 'react';
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

const CreateGroupDialog = (props) => {
	const [selected, setSelected] = useState([]);
	const [groupName, setGroupName] = useState('');
	const { onClose, open, handleAlert, friendsList } = {
		onClose: props.onClose,
		open: props.open,
		handleAlert: props.handleAlert,
		friendsList: props.friendsList,
	};

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

	const handleCreateGroup = () => {
		const success = false; // TODO This will be connected to backend
		let message;
		let color;

		if (success) {
			message = 'You successfully created a group with name ' + groupName;
			color = 'success';
		} else {
			message = 'There was a problem. Please try again';
			color = 'error';
		}

		handleAlert({
			message,
			color,
		});
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
