<template>
    <div class="2xl:container md:mx-auto flex flex-row bg-gray-50">
        <div class="basis-1/5">
            <AdminSidebar />
        </div>
        <div class="class-list basis-5/6 mt-10 bg-gray-50">
            <h2 class="app-title">Submit a Show</h2>
            <hr style="width:90%;text-align:left;margin-left:0;margin-top: 10px;height:3px;">
            <br>
            <form class="form">

                <div class="relative z-0 w-full mb-6 group">
                    <label for="countries" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                        Movie
                    </label>
                    <select id="movieId" v-model="show.movieId" required
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <option v-for="movie in movies" :key="movie.id" :value="movie.id">{{ movie.title }}</option>
                    </select>
                </div>
                <div class="grid md:grid-cols-2 md:gap-6">
                    <div class="relative z-0 w-full mb-6 group">
                        <input type="time" name="floating_first_name" id="floating_first_name"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required v-model="time" />
                        <label for="floating_first_name"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Start time</label>
                    </div>
                    <div class="relative z-0 w-full mb-6 group">
                        <input type="date" name="floating_last_name" id="floating_last_name"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required v-model="date" />
                        <label for="floating_last_name"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                            Date</label>
                    </div>
                </div>
                <div class="grid md:grid-cols-2 md:gap-6">
                    <div class="relative z-0 w-full mb-6 group">
                        <label for="base-input" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                            Duration</label>
                        <input type="number" id="base-input" v-model="show.duration" required
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    </div>
                    <div class="relative z-0 w-full mb-6 group">
                        <label for="countries" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                            Cinema Hall</label>
                        <select id="countries" v-model="show.cinemaHallId" required
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            <option v-for="cinemaHall in cinemaHalls" :key="cinemaHall.id" :value="cinemaHall.id">{{
                                cinemaHall.name }}</option>
                        </select>
                    </div>
                </div>
                <label for="countries" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    Seat Types and Prices</label>
                <div class="grid md:grid-cols-2 md:gap-6">
                    <div v-for="index in numType" :key="index" class="form-row">

                        <div class="relative z-0 w-full mb-6 group">
                            <label :for="`seatType${index}`"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                Seat Type {{ index }}</label>
                            <select :id="`seatType${index - 1}`" v-model="seatTypes[index - 1]" required
                                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                <option>Regular</option>
                                <option>Premium</option>
                                <option>Accessible</option>
                                <option>EmergencyExit</option>
                                <option>Couple</option>
                            </select>
                        </div>
                        <div class="relative z-0 w-full mb-6 group">
                            <input type="number" :id="`seatPrice${index - 1}`" v-model="seatPrices[index - 1]" required
                                class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                placeholder=" " />
                            <label :for="`seatPrice${index}`"
                                class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Seat
                                Price {{ index }}</label>
                        </div>
                    </div>
                    
                </div>
                <button @click="addRow"
                    class="mt-10 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-teal-300 to-lime-300 group-hover:from-teal-300 group-hover:to-lime-300 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-lime-800">
                    <span
                        class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Add Row
                    </span>
                </button>
                <button @click="removeRow"
                    class="mt-10 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-teal-300 to-lime-300 group-hover:from-teal-300 group-hover:to-lime-300 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-lime-800">
                    <span
                        class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Remove Row
                    </span>
                </button>
                <button @click="submitData(); $event.preventDefault()"
                    class="mt-10 float-right relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-teal-300 to-lime-300 group-hover:from-teal-300 group-hover:to-lime-300 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-lime-800">
                    <span
                        class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Submit
                    </span>
                </button>
                <Notification :status="status" :object="'phim'" :action="'Thêm'" :isShowNoti="showNoti"
                    v-if="showNoti == 'Ok'">
                </Notification>
            </form>
        </div>
    </div>
</template>
<script>
import AdminSidebar from '~/components/AdminSidebar.vue';
export default {
    data() {
        return {
            show: {},
            movies: null,
            time: null,
            date: null,
            cinemaHalls: null,
            seatTypes: [],
            seatPrices: [],
            numType: 1,
            showNoti: "",
            status: "",
        };
    },
    async fetch() {
        try {
            await this.$axios.get(`http://localhost:8080/api/movie`).then((res) => {
                this.$data.movies = res.data;
            });
            await this.$axios.get(`http://localhost:8080/api/cinemaHall/all`).then((res) => {
                this.$data.cinemaHalls = res.data;
            });
        }
        catch (error) {
            console.log(error);
        }
    },
    methods: {
        addRow() {
            ++this.numType;
        },
        removeRow() {
            if (this.numType > 0) {
                --this.numType;
            }
        },
        async submitData() {
            const data = {
                seatTypes: this.seatTypes,
                seatPrices: this.seatPrices
            };
            const startTime = `${this.date}T${this.time}:00.000+00:00`;
            // Send the data to the API
            this.show.startTime = startTime;
            this.show.seatTypes = this.seatTypes;
            this.show.seatPrices = this.seatPrices;
            this.show.createOn = new Date().toISOString();
            // alert(this.show);
            console.log(this.show);
            await this.$axios.post("http://localhost:8080/api/show/create", this.show).then((response) => {
                console.log(response);
                this.status = "thành công";
                this.showNoti = "Ok";
                setTimeout(() => {
                    this.showNoti = "";
                }, 1500);
            });
        }
    },
    components: { AdminSidebar }
};

</script>
<style scoped>
h2 {
    font-size: 26px;
    margin: 0 0 0px 30px;
    /* text-align: center; */
}

.form-row {
    margin-bottom: 10px;
}

.form-column {
    display: inline-block;
    margin-right: 10px;
}

.form {
    width: 800px;
    margin-left: 150px;
}
</style>