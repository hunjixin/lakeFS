import React, {createContext, useCallback, useEffect, useState} from "react";
import {Route, Routes} from "react-router-dom";

import Button from "react-bootstrap/Button";

import {AuthLayout} from "../../../lib/components/auth/layout";
import {useAPIWithPagination} from "../../../lib/hooks/api";
import {auth} from "../../../lib/api";
import useUser from "../../../lib/hooks/user";
import {ConfirmationButton} from "../../../lib/components/modals";
import {EntityActionModal} from "../../../lib/components/auth/forms";
import {Paginator} from "../../../lib/components/pagination";
import {useRouter} from "../../../lib/hooks/router";
import {Link} from "../../../lib/components/nav";
import {
    ActionGroup,
    ActionsBar,
    Checkbox,
    DataTable,
    AlertError,
    FormattedDate,
    Loading,
    RefreshButton
} from "../../../lib/components/controls";
import UserPage from "./user";
import { disallowPercentSign, INVALID_USER_NAME_ERROR_MESSAGE } from "../validation";

const USER_NOT_FOUND = "unknown";
export const GetUserEmailByIdContext = createContext();


const UsersContainer = ({nextPage, refresh, setRefresh, error, loading, userListResults}) => {
    const { user } = useUser();
    const currentUser = user;

    const router = useRouter();
    const after = (router.query.after) ? router.query.after : "";
    const [selected, setSelected] = useState([]);
    const [deleteError, setDeleteError] = useState(null);
    const [showCreate, setShowCreate] = useState(false);
    

    useEffect(() => { setSelected([]); }, [refresh, after]);

    if (error) return <AlertError error={error}/>;
    if (loading) return <Loading/>;

    return (
        <>
            <ActionsBar>
                <UserActionsActionGroup selected={selected}
                                        onClickCreate={() => setShowCreate(true)}
                                        onConfirmDelete={() => {
                                            auth.deleteUsers(selected.map(u => u.id))
                                                .catch(err => setDeleteError(err))
                                                .then(() => {
                                                    setSelected([]);
                                                    setRefresh(!refresh);
                                                })}}/>
                <ActionGroup orientation="right">
                    <RefreshButton onClick={() => setRefresh(!refresh)}/>
                </ActionGroup>
            </ActionsBar>
            <div className="auth-learn-more">
                Users are entities that access and use lakeFS. <a href="https://docs.lakefs.io/reference/authentication.html" target="_blank" rel="noopener noreferrer">Learn more.</a>
            </div>

            {(!!deleteError) && <AlertError error={deleteError}/>}

            <EntityActionModal
                show={showCreate}
                onHide={() => setShowCreate(false)}
                onAction={userId => {
                    return auth.createUser(userId).then(() => {
                        setSelected([]);
                        setShowCreate(false);
                        setRefresh(!refresh);
                    });
                }}
                title={"Create Integration User"}
                placeholder={"Integration Name (e.g. Spark)"}
                actionName={"Create"}
                validationFunction={disallowPercentSign(INVALID_USER_NAME_ERROR_MESSAGE)}
            />

            <DataTable
                results={userListResults}
                headers={['', 'User ID', 'Created At']}
                keyFn={user => user.id}
                rowFn={user => [
                    <Checkbox
                        disabled={(!!currentUser && currentUser.id === user.id)}
                        name={user.id}
                        onAdd={() => setSelected([...selected, user])}
                        onRemove={() => setSelected(selected.filter(u => u !== user))}
                    />,
                    <Link href={{pathname: '/auth/users/:userId', params: {userId: user.id}}}>
                        {user.email || user.id}
                    </Link>,
                    <FormattedDate dateValue={user.creation_date}/>
                ]}/>

            <Paginator
                nextPage={nextPage}
                after={after}
                onPaginate={after => router.push({pathname: '/auth/users', query: {after}})}
            />
        </>
    );
};

const UserActionsActionGroup = ({selected, onClickCreate, onConfirmDelete }) => {

    return (
        <ActionGroup orientation="left">
            <Button
                variant="success"
                onClick={onClickCreate}>
                {"Create Integration User"}
            </Button>
            <ConfirmationButton
                onConfirm={onConfirmDelete}
                disabled={(selected.length === 0)}
                variant="danger"
                msg={`Are you sure you'd like to delete ${selected.length} users?`}>
                Delete Selected
            </ConfirmationButton>
        </ActionGroup>
    );
}

const UsersPage = ({nextPage, refresh, setRefresh, error, loading, userListResults}) => {
    return (
        <AuthLayout activeTab="users">
            <UsersContainer
                refresh={refresh}
                loading={loading}
                error={error}
                nextPage={nextPage}
                setRefresh={setRefresh}
                userListResults={userListResults}
            />
        </AuthLayout>
    );
};

const UsersIndexPage = () => {
    const [refresh, setRefresh] = useState(false);
    const [usersList, setUsersList] = useState([]);
    const router = useRouter();
    const after = (router.query.after) ? router.query.after : "";
    const { results, loading, error, nextPage } =  useAPIWithPagination(() => {
        return auth.listUsers('', after);
    }, [after, refresh]);

    useEffect(() => {
        setUsersList(results);
    }, [results, refresh]);

    const getUserEmailById = useCallback((id) => {
        const userRecord = usersList.find(user => user.id === id);
        // return something, so we don't completely break the state
        // this can help us track down issues later on
        if (!userRecord) {
            return USER_NOT_FOUND;
        }

        return userRecord.email || userRecord.id;
    }, [usersList]);

    return (
        <GetUserEmailByIdContext.Provider value={getUserEmailById}>
            <Routes>
                <Route path=":userId/*" element={<UserPage getUserEmailById={getUserEmailById} />} />
                <Route path="" element={
                    <UsersPage 
                        refresh={refresh}
                        loading={loading}
                        error={error}
                        nextPage={nextPage}
                        setRefresh={setRefresh}
                        userListResults={usersList}
                    />
                } />
            </Routes>
        </GetUserEmailByIdContext.Provider>
    )
}

export default UsersIndexPage;
