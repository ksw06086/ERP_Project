import { TextField, Box, Card, Button, Typography } from '@mui/material';
import { useState } from 'react'
import { useCookies } from 'react-cookie';
import { useUserStore } from '../../../stores';
import { signInApi } from '../../../apis';

export default function SignIn(props) {
    const [userEmail, setUserEmail] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [cookies, setCookies] = useCookies();

    const { setAuthView } = props;

    const { user, setUser } = useUserStore();

    const signInHandler = async () => {
        if(userEmail.length === 0 || userPassword.length === 0){
            alert('이메일과 비밀번호를 입력하세요.');
            return;
        }

        const data = {
            userEmail,
            userPassword
        }

        const signInResponse = await signInApi(data);

        if(!signInResponse || !signInResponse.result){
            console.log('로그인에 실패했습니다.');
            return;
        }

        const { token, exprTime, user } = signInResponse.data;
        const expires = new Date();
        expires.setMilliseconds(expires.getMilliseconds() + exprTime);

        setCookies('token', token, { expires });
        setUser(user);
    }

    return (
        <Card sx={{ minWidth: 275, width: "25vw", padding: 5 }}>
            <Box>
                <Typography variant='h5'>로그인</Typography>
            </Box>
            <Box height={'50vh'}>    
                <TextField
                    fullWidth
                    label="이메일"
                    type='email'
                    variant='standard'
                    onChange={(e)=>{setUserEmail(e.target.value)}}
                />
                <TextField
                    fullWidth
                    label="비밀번호"
                    type='password'
                    variant='standard'
                    onChange={(e)=>{setUserPassword(e.target.value)}}
                />
            </Box>
            <Box component={'div'}>
                <Button fullWidth variant="contained" onClick={()=>{ signInHandler(); }}>로그인</Button>
            </Box>
            <Box component={'div'} display={'flex'} mt={2}>
                <Typography>신규 사용자 이신가요?</Typography>
                <Typography fontWeight={800} ml={1} onClick={()=>{ setAuthView(true); }}>회원가입</Typography>
            </Box>
        </Card>
    )
}
