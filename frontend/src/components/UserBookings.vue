<template>
  <div class="bg-gray-700 bg-opacity-50 rounded-3xl p-5 my-10" v-if="bookings.length">

    <h2 class="text-center">Bookings</h2>
    <div class="container mx-auto p-3 flex justify-evenly" v-for="(booking, index) in bookings" :key="booking.id">
        <div>
          <h4>{{ booking.schedule.movie.name }}</h4>
          <h5 class="pt-3"><strong>Cinema:</strong> {{ booking.schedule.cinema.name }} -
            {{ booking.schedule.cinemaHall.name }}</h5>
          <p><strong>Duration:</strong> {{ booking.schedule.movie.duration }} minutes</p>
          <p><strong>Average Rating:</strong> {{ averageRatings[booking.schedule.movie.id] || 'Loading...' }}%</p>
          <p><strong>Showtime:</strong> {{ new Date(booking.schedule.localDateTime).toLocaleString() }}</p>
          <p><strong>Booked Seats:</strong> {{ booking.bookedSeats.join(', ') }}</p>
          <hr v-if="isLastBooking(booking)" class="mt-6">
        </div>
        <user-rating :movieId="booking.schedule.movie.id" @ratingSubmitted="updateAverageRating"></user-rating>

    </div>

  </div>

  <p v-else>You have no bookings yet...</p>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import UserRating from "@/components/UserRating.vue";
const isLastBooking = (booking) => bookings.value.indexOf(booking) !== bookings.value.length - 1;
const bookings = ref([]);
const newRating = ref(0);
const averageRatings = ref({});

const fetchBookings = async () => {
  const userData = localStorage.getItem('userData');
  if (!userData) return;

  const user = JSON.parse(userData);
  try {
    const response = await fetch(`http://localhost:9000/bookings/user/${user.googleId}`);
    if (!response.ok) throw new Error('Failed to fetch bookings');
    bookings.value = await response.json();

    bookings.value.forEach((booking) => fetchAverageRating(booking.schedule.movie.id));
  } catch (error) {
    console.error('Error fetching bookings:', error);
  }
};
const fetchAverageRating = async (movieId) => {
  try {
    const response = await fetch(`http://localhost:9000/movies/${movieId}`);
    if (!response.ok) throw new Error('Failed to fetch movie data');

    const movieData = await response.json();

    if (movieData.averageRating !== undefined) {
      averageRatings.value[movieId] = movieData.averageRating;
    } else {
      console.warn(`No averageRating found for movie ${movieId}`);
    }
  } catch (error) {
    console.error(`Error fetching average rating for movie ${movieId}:`, error);
  }
};

const updateAverageRating = (updateObject) => {
    averageRatings.value[updateObject.movieId] = updateObject.newRating;
    window.dispatchEvent(new Event("updateRating"));
};

onMounted(() => {
  fetchBookings();
});

</script>

<style scoped>
</style>
