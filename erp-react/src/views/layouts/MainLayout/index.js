import React, { useEffect, useState } from 'react'
import Athentication from '../../Authentication'
import { useUserStore } from '../../../stores'
import { useCookies } from 'react-cookie'
import axios from 'axios'
import ErpMain from '../../ErpMain'
import Navigation from '../../Navigation'

export default function MainLayout() {

    const [boardResponse, setBoardResponse] = useState('');
    const [cookies] = useCookies();
    const { user } = useUserStore();

    const getBoard = async (token) => {
        const requestOptions = {
            headers: {
                Authorization: `Bearer ${token}`,
            }
        }
    }

    useEffect(() => {
        const token = cookies.token;
        if(token) getBoard(token);
        else setBoardResponse('');
    }, [cookies.token]);

    return (
        <>
            <Navigation />
            { user ? (<ErpMain />) : (<Athentication />) }
        </>
    )
}
