<template>
    <form action="#">
        <h3 class="mb-10 mt-5 text-lg font-medium leading-none text-gray-900 dark:text-white">Show timing selection</h3>
        <div class="grid gap-4 mb-20 sm:grid-cols-2">
            <div class="mb-10">
                <label for="date" class="text-xl mr-20">Choose a Date:</label>
                <input type="date" id="date" v-model="selectedDate" @change="filterShows" class="custom-date-input" />

            </div><br>
            <div class="cnt">
                <label for="date" class="text-xl">Choose a Show:</label>
                <div class="show-list " v-for="show in showDate" :key="show.id">
                    <button class="show-button-choice"  @click="selectShow(show); $event.preventDefault()"
                        :class="{ 'selected': show.selected }" v-if="show == choice">{{ show.startTime.toLocaleTimeString() }}</button>
                    <button class="show-button" @click="selectShow(show); $event.preventDefault()"
                        :class="{ 'selected': show.selected }" v-else>{{ show.startTime.toLocaleTimeString() }}</button>

                </div>
            </div>
        </div>
        <button type="submit"
            class="text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 left"
            @click="submit(); $event.preventDefault()">
            Next Step: Selete Seats
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
    },
    data() {
        return {
            selectedDate: null,
            shows: [],
            showDate: [],
            choice: null,
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
                        startTime: new Date(show.startTime),
                    };
                });
                // console.log(this.shows);
            });
        }
        catch (error) {
            console.log(error);
        }
    },
    methods: {
        filterShows() {
            const selectedDate = new Date(this.$data.selectedDate);
            const filteredShows = this.shows.filter((show) => {
                const showDate = new Date(show.startTime);
                // console.log(showDate);
                console.log(showDate.toDateString() + "/" + selectedDate.toDateString());
                return showDate.toDateString() === selectedDate.toDateString();
            });
            console.log(filteredShows);
            // Update the shows array with filteredShows
            this.$data.showDate = filteredShows;

        },
        formatDate(date) {
            // Format the date in the desired format (e.g., yyyy-MM-dd) using JavaScript Date methods
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
        selectShow(selectedShow) {
            this.showDate.forEach(show => {
                show.selected = show === selectedShow;
            });
            this.$data.choice = selectedShow;
        },
        submit() {
            console.log(this.choice);
            if (this.choice==null) {
                alert('Please choose a Show.');
                return;
            }
            this.$emit("showChoice",this.choice);
        }
    },

}
</script>
<style scoped>
.selected {
    background-color: rgb(202, 202, 202);
}

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
    width: 50px;
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
}

.show-button-choice {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
    margin-right: 15px;
    background-color: #000000;
    color: #ffffff;
}

.left {
    margin-left: 650px !important;
}
</style>