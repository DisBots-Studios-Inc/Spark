import { Event } from "../Interfaces";
import ActivityTypes from "../util/ActivityTypes";
import chalk from "chalk";

export const event: Event = {
    name: "ready",
    run: (client) => {
        client.user.setActivity("to your messages!", { type: ActivityTypes.LISTENING });
        console.log('[' + chalk.cyan('Client_info') + ']' + `${client.user.username} is online!`);
    }
}