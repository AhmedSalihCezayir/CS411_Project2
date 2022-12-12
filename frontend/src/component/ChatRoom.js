import React, { useEffect, useState } from 'react';
import { over } from 'stompjs';
import SockJS from 'sockjs-client';
import IconButton from '@mui/material/IconButton';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import Grid from '@mui/material/Grid';
import AddFriendsDialog from './AddFriendsDialog';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import CreateGroupDialog from './CreateGroupDialog';
import {
	Typography,
	Button,
	Input,
	Box,
	ListItem,
	ListItemButton,
	List,
	TextField,
	Chip,
} from '@mui/material';
import Register from './Register';
import Logo from '../icons/logo.png';
import axios from 'axios';

var stompClient = null;

const Alert = React.forwardRef(function Alert(props, ref) {
	return <MuiAlert elevation={6} ref={ref} variant='filled' {...props} />;
});

const ChatRoom = () => {
	// Each key hold username, each value hold list of messages
	// which are sent by particular user
	const [privateChats, setPrivateChats] = useState(new Map());
	const [publicChats, setPublicChats] = useState([]);
	const [tab, setTab] = useState('CHATROOM');
	// Hold data about if we are connected, message and user
	const [userData, setUserData] = useState({
		username: '',
		receivername: '',
		connected: false,
		message: '',
		messageDate: '',
	});
	const [addFriendsDialogOpen, setAddFriendsDialogOpen] = useState(false);
	const [showAddFriendAlert, setShowAddFriendAlert] = useState(false);
	const [alert, setAlert] = useState({
		message: '',
		color: '',
	});
	const [friendsList, setFriendsList] = useState([
		'Ahmed Salih Cezayir',
		'İsmail Sergen Göçmen',
		'Akın Kutlu',
		'Mert Barkın Er',
	]);
	const [createGroupDialogOpen, setCreateGroupDialogOpen] = useState(false);
	const [showCreateGroupAlert, setShowCreateGroupAlert] = useState(false);

	useEffect(() => {
		console.log(userData);
	}, [userData]);

	const connect = () => {
		let Sock = new SockJS('http://localhost:8080/ws');
		stompClient = over(Sock);
		stompClient.connect({}, onConnected, onError);
	};

	const onConnected = () => {
		setUserData({ ...userData, connected: true });
		stompClient.subscribe('/chatroom/public', onMessageReceived);
		stompClient.subscribe(
			'/user/' + userData.username + '/private',
			onPrivateMessage
		);
		userJoin();
	};

	const userJoin = () => {
		var chatMessage = {
			senderName: userData.username,
			status: 'JOIN',
		};
		stompClient.send('/app/message', {}, JSON.stringify(chatMessage));
	};

	const onMessageReceived = (payload) => {
		var payloadData = JSON.parse(payload.body);
		switch (payloadData.status) {
			case 'JOIN':
				if (!privateChats.get(payloadData.senderName)) {
					privateChats.set(payloadData.senderName, []);
					setPrivateChats(new Map(privateChats));
				}
				break;
			case 'MESSAGE':
				publicChats.push(payloadData);
				setPublicChats([...publicChats]);
				break;
		}
	};

	const onPrivateMessage = (payload) => {
		console.log(payload);
		var payloadData = JSON.parse(payload.body);
		if (privateChats.get(payloadData.senderName)) {
			privateChats.get(payloadData.senderName).push(payloadData);
			setPrivateChats(new Map(privateChats));
		} else {
			let list = [];
			list.push(payloadData);
			privateChats.set(payloadData.senderName, list);
			setPrivateChats(new Map(privateChats));
		}
	};

	const onError = (err) => {
		console.log(err);
	};

	const handleMessage = (event) => {
		const { value } = event.target;
		const month = [
			'Jan',
			'Feb',
			'Mar',
			'Apr',
			'May',
			'Jun',
			'Jul',
			'August',
			'Sep',
			'Oct',
			'Nov',
			'Dec',
		];
		var today = new Date();
		const date =
			month[today.getMonth()] +
			'.' +
			today.getDate() +
			' ' +
			today.getHours() +
			':' +
			today.getMinutes();
		setUserData({ ...userData, message: value, messageDate: date });
	};

	const sendValue = () => {
		if (stompClient) {
			var chatMessage = {
				senderName: userData.username,
				message: userData.message,
				date: userData.messageDate,
				status: 'MESSAGE',
			};
			console.log(chatMessage);
			stompClient.send('/app/message', {}, JSON.stringify(chatMessage));
			setUserData({ ...userData, message: '', messageDate: '' });
		}
	};

	const sendPrivateValue = () => {
		if (stompClient) {
			var chatMessage = {
				senderName: userData.username,
				receiverName: tab,
				message: userData.message,
				date: userData.messageDate,
				status: 'MESSAGE',
			};

			if (userData.username !== tab) {
				privateChats.get(tab).push(chatMessage);
				setPrivateChats(new Map(privateChats));
			}
			stompClient.send(
				'/app/private-message',
				{},
				JSON.stringify(chatMessage)
			);
			setUserData({ ...userData, message: '', messageDate: '' });
		}
	};

	const handleUsername = (event) => {
		const { value } = event.target;
		setUserData({ ...userData, username: value });
	};

	const registerUser = async () => {
		await axios.post(`http://localhost:8080/user/create`, {
			name: userData.username,
		});

		setFriendsList(friendsList.data);
		connect();
	};

	// These are for vertical dots menu
	const [anchorEl, setAnchorEl] = React.useState(null);
	const open = Boolean(anchorEl);
	const handleClick = (event) => {
		setAnchorEl(event.currentTarget);
	};
	const handleClose = () => {
		setAnchorEl(null);
	};

	const openFriendDialog = () => {
		setAnchorEl(false);
		setAddFriendsDialogOpen(true);
	};

	const closeFriendDialog = () => {
		setAddFriendsDialogOpen(false);
	};

	const handleAlert = (alert) => {
		setAlert({ message: alert.message, color: alert.color });
		setShowAddFriendAlert(true);
	};

	const handleCloseAlert = () => {
		setShowAddFriendAlert(false);
		setShowCreateGroupAlert(false);
	};

	const openCreateGroupDialog = () => {
		setAnchorEl(false);
		setCreateGroupDialogOpen(true);
	};

	const closeCreateGroupDialog = () => {
		setCreateGroupDialogOpen(false);
	};

	const handleGroupAlert = (alert) => {
		setAlert({ message: alert.message, color: alert.color });
		setShowCreateGroupAlert(true);
	};

	const logoSize = {
		width: 240,
		height: 170,
	};

	return (
		<div className='container'>
			<Box display='flex' justifyContent='center' margin='auto'>
				<img src={Logo} alt='Logo' style={logoSize} />
			</Box>
			<div className='container' justifyContent='center' margin='auto'>
				{userData.connected ? (
					<div className='chat-box'>
						<div className='member-list'>
							<Grid container justifyContent='flex-end'>
								<IconButton>
									<AccountCircleIcon />
								</IconButton>
								<IconButton
									aria-controls={
										open
											? 'demo-positioned-menu'
											: undefined
									}
									aria-haspopup='true'
									aria-expanded={open ? 'true' : undefined}
									onClick={handleClick}
								>
									<MoreVertIcon />
								</IconButton>
								<Menu
									anchorEl={anchorEl}
									open={open}
									onClose={handleClose}
								>
									<MenuItem onClick={openCreateGroupDialog}>
										Create Group
									</MenuItem>
									<MenuItem onClick={openFriendDialog}>
										Add Friend
									</MenuItem>
									<MenuItem onClick={handleClose}>
										Logout
									</MenuItem>
								</Menu>
							</Grid>

							<AddFriendsDialog
								open={addFriendsDialogOpen}
								onClose={closeFriendDialog}
								handleAlert={handleAlert}
								currentUser={userData.username}
							/>

							<Snackbar
								open={showAddFriendAlert}
								autoHideDuration={4000}
								onClose={handleCloseAlert}
								anchorOrigin={{
									vertical: 'top',
									horizontal: 'center',
								}}
							>
								<Alert
									onClose={handleCloseAlert}
									severity={alert.color}
									sx={{ width: '100%' }}
								>
									{alert.message}
								</Alert>
							</Snackbar>

							<CreateGroupDialog
								open={createGroupDialogOpen}
								friendsList={friendsList}
								onClose={closeCreateGroupDialog}
								handleAlert={handleGroupAlert}
								currentUser={userData.username}
							/>

							<Snackbar
								open={showCreateGroupAlert}
								autoHideDuration={4000}
								onClose={handleCloseAlert}
								anchorOrigin={{
									vertical: 'top',
									horizontal: 'center',
								}}
							>
								<Alert
									onClose={handleCloseAlert}
									severity={alert.color}
									sx={{ width: '100%' }}
								>
									{alert.message}
								</Alert>
							</Snackbar>

							<List
								style={{
									display: 'flex',
									flexDirection: 'column',
								}}
							>
								<Button
									onClick={() => {
										setTab('CHATROOM');
									}}
								>
									Chatroom
								</Button>
								{[...privateChats.keys()].map((name, index) => (
									<Button
										color='secondary'
										onClick={() => {
											setTab(name);
										}}
										key={index}
									>
										{name}
									</Button>
								))}
							</List>
						</div>
						{tab === 'CHATROOM' && (
							<div className='chat-content'>
								<ul
									className='chat-messages'
									style={{ overflowY: 'scroll' }}
								>
									{publicChats.map((chat, index) => (
										<li
											className={`message ${
												chat.senderName ===
													userData.username && 'self'
											}`}
											key={index}
										>
											{chat.senderName !==
												userData.username && (
												<Chip
													color='secondary'
													label={chat.senderName}
												/>
											)}
											{chat.senderName ==
											userData.username ? (
												<>
													<div className='message-date'>
														<Typography
															sx={{
																fontSize: 11,
															}}
														>
															{chat.date}
														</Typography>
													</div>
													<div className='message-data'>
														{chat.message}
													</div>
												</>
											) : (
												<>
													<div className='message-data'>
														{chat.message}
													</div>
													<div className='message-date'>
														<Typography
															sx={{
																fontSize: 11,
															}}
														>
															{chat.date}
														</Typography>
													</div>
												</>
											)}
											{chat.senderName ===
												userData.username && (
												<Chip
													color='primary'
													label={chat.senderName}
												/>
											)}
										</li>
									))}
								</ul>

								<Box
									className='send-message'
									display='flex'
									justifyContent='space-between'
								>
									<Input
										type='text'
										placeholder='Enter your Message'
										value={userData.message}
										onChange={handleMessage}
										sx={{ width: 8 / 10 }}
									/>
									<Button
										type='button'
										className='send-button'
										onClick={sendValue}
										variant='contained'
									>
										Send
									</Button>
								</Box>
							</div>
						)}
						{tab !== 'CHATROOM' && (
							<div className='chat-content'>
								<ul
									className='chat-messages'
									style={{ overflowY: 'scroll' }}
								>
									{[...privateChats.get(tab)].map(
										(chat, index) => (
											<li
												className={`message ${
													chat.senderName ===
														userData.username &&
													'self'
												}`}
												key={index}
											>
												{chat.senderName !==
													userData.username && (
													<Chip
														color='secondary'
														label={chat.senderName}
													/>
												)}
												{chat.senderName ==
												userData.username ? (
													<>
														<div className='message-date'>
															<Typography
																sx={{
																	fontSize: 11,
																}}
															>
																{chat.date}
															</Typography>
														</div>
														<div className='message-data'>
															{chat.message}
														</div>
													</>
												) : (
													<>
														<div className='message-data'>
															{chat.message}
														</div>
														<div className='message-date'>
															<Typography
																sx={{
																	fontSize: 11,
																}}
															>
																{chat.date}
															</Typography>
														</div>
													</>
												)}
												{chat.senderName ===
													userData.username && (
													<Chip
														color='primary'
														label={chat.senderName}
													/>
												)}
											</li>
										)
									)}
								</ul>

								<Box
									className='send-message'
									display='flex'
									justifyContent='space-between'
								>
									<Input
										type='text'
										placeholder='Enter your Message'
										value={userData.message}
										onChange={handleMessage}
										sx={{ width: 8 / 10 }}
									/>
									<Button
										className='send-button'
										onClick={sendPrivateValue}
										variant='contained'
									>
										Send
									</Button>
								</Box>
							</div>
						)}
					</div>
				) : (
					<Box justifyContent='center' margin='auto'>
						<Box
							display='flex'
							flexDirection={'column'}
							alignItems='center'
							justifyContent={'center'}
							margin='auto'
							marginTop={10}
						>
							<TextField
								margin='normal'
								id='name'
								label='Name'
								variant='standard'
								name='name'
								required
								value={userData.username}
								onChange={handleUsername}
							/>
							<Button
								sx={{ marginTop: 4 }}
								variant='contained'
								onClick={registerUser}
							>
								{' '}
								Login{' '}
							</Button>
						</Box>
					</Box>
				)}
			</div>
		</div>
	);
};

export default ChatRoom;
