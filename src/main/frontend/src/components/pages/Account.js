import React, { useState, useEffect } from "react";
import NavigationBar from "./NavigationBar";
import axios from "axios";
import "../style/Account.css";

const Account = () => {
    const [account, setAccount] = useState(null);
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("jwtToken");

        try {
            const response = await axios.post("http://localhost:8080/api/v1/account/password", null, {
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                params: {
                    newPassword: password
                }
            });
            if(response.status === 200) {
                alert("Successfully updated password!")
            }
        } catch (error) {
            setErrorMessage("Failed to update password. Please try again.");
        }
    }

    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        const fetchAccount = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/v1/account", {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                setAccount(response.data);
            } catch (error) {
                alert("Failed to fetch account data. Please try again.");
            }
        }
        fetchAccount();
    }, []);

    return (
        <div>
            <NavigationBar />
            <div className="account-box">
                {account ? (
                    <div className="account-details">
                        <h1>Account details</h1>
                        <p>Username: {account.username}</p>
                        <p>Email: {account.email}</p>
                        <section className="form">
                            <form onSubmit={handleSubmit} className="password-form">
                                <label>Change password</label>
                                <input
                                    type="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                                <button type="submit">Submit change</button>
                            </form>
                        </section>
                    </div>
                    ) : (<p>Loading...</p>)}
            </div>
        </div>
    );
}

export default Account;