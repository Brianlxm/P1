import { UserInterface } from "../interfaces/UserInterface";


//store for global data storage (user info)
export const state:any = {
    userSessionData: {
        userId:0,
        username:""
    } as UserInterface, 
}