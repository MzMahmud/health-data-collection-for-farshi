class AjaxUtil {
    static async #extractResponseJson(response) {
        if (!response.ok) {
            throw new Error("Server Responded with NOT OK");
        }
        const responseJson = await response.json();
        if (responseJson.success == null || responseJson.success === false) {
            throw new Error(responseJson.error ?? "Server Error");
        }
        return responseJson;
    }

    static async postAsJson(url, data) {
        const response = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        return await this.#extractResponseJson(response);
    }

    static async getPayload(url) {
        const response = await fetch(url);
        const responseJson = await this.#extractResponseJson(response);
        return responseJson.payload;
    }

    static async sendDeleteRequest(url) {
        const response = await fetch(url, {method: 'DELETE'});
        return await this.#extractResponseJson(response);
    }
}