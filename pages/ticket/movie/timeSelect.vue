<template>
    <form action="#">
        <h3 class="mb-10 mt-5 text-lg font-medium leading-none text-gray-900 dark:text-white">Show timing selection</h3>
        <div class="grid gap-4 mb-4 sm:grid-cols-2">
            <!-- <div>
            <label for="username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Username</label>
            <input type="text" name="username" id="username" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="username.example" required="">
        </div>
        <div>
            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
            <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="">
        </div>
        <div>
            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
            <input type="password" name="password" id="password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="•••••••••" required="">
        </div>                        <div>
            <label for="confirm-password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm password</label>
            <input type="password" name="confirm-password" id="confirm-password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="•••••••••" required="">
        </div> -->
            <div class="mb-10">
                <label for="date" class="text-xl mr-20">Choose a Date:</label>
                <input type="date" id="date" v-model="selectedDate" @change="fetchData" class="custom-date-input" />

            </div><br>
            <div class="cnt">
                <label for="date" class="text-xl">Choose a Show:</label>
                <div class="show-list ">
                    <button class="show-button" v-for="show in shows" :key="show.id" @click="selectShow(show)">
                        {{ show.startTime }}
                    </button>
                </div>
            </div>
        </div>
        <button type="submit"
            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Next Step: Payment Info
        </button>
    </form>
</template>
<script>
export default {

    props: {
        movieId: { // Correctly define movieId as a prop
            type: String,
            default: "0",
        },
        selectedDate: null,
    },
    data() {
        return {
            shows: [],
        }
    },
    async fetch() {
        try {
            let url = `http://localhost:8080/api/show/movie=${this.movieId}`;
            await this.$axios.get(url).then((response) => {
                this.shows = response.data.map((show) => {
                    return {
                        id: show.id,
                        createOn: new Date(show.createOn).toLocaleDateString(),
                        duration: show.duration,
                        startTime: new Date(show.startTime).toLocaleTimeString(),
                    };
                });
            });
        }
        catch (error) {
            console.log(error);
        }
    },
    methods: {
        filterShows() {
            const selectedDate = new Date(this.selectedDate);
            const filteredShows = this.shows.filter((show) => {
                const showDate = new Date(show.startTime);
                return showDate.toDateString() === selectedDate.toDateString();
            });
            // Update the shows array with filteredShows
            this.shows = filteredShows;
        },
        formatDate(date) {
            // Format the date in the desired format (e.g., yyyy-MM-dd) using JavaScript Date methods
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
    },

}
</script>
<style scoped>
.cnt {
    width: 1000px;
}

.custom-date-input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    font-size: 16px;
    color: #333;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: 250px;
}

.custom-date-input:focus {
    outline: none;
    border-color: #2196f3;
    box-shadow: 0 0 4px rgba(33, 150, 243, 0.6);
}

.show-title {
    text-align: center;
    font-size: 20px;
    margin-bottom: 20px;
    display: inline-block;
}

.show-list {
    display: inline-block;
    margin-left: 75px;

}

.show-button {
    padding: 10px 20px;
    background-color: #f0f0f0;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
    margin-right: 15px;
}

.show-button:hover {
    background-color: #ccc;
}</style>