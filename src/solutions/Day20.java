package solutions;

import java.util.*;

public class Day20 {

    public void firstPart(String[] input) {
        List<Particle> particles = parseParticles(input);
        double minAverageZeroDistance = Double.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < 1000; i++) {
            for (Particle p :
                    particles) {
                p.iteration();
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).getAverageZeroDistance() < minAverageZeroDistance) {
                minAverageZeroDistance = particles.get(i).getAverageZeroDistance();
                minIndex = i;
            }
        }
        System.out.println(minIndex);
    }

    public void secondPart(String[] input) {
        List<Particle> particles = parseParticles(input);
        int iterationsWithoutCollisions = 0;
        boolean jobDone = false;
        while (!jobDone) {

            for (Particle p :
                    particles) {
                p.iteration();
            }
            List<Particle> particlesWithoutCollisions = getParticlesWithoutCollisions(particles);

            if (particles.size() == particlesWithoutCollisions.size()) {
                iterationsWithoutCollisions++;
            } else {
                particles = getParticlesWithoutCollisions(particles);
                iterationsWithoutCollisions = 0;
            }
            if (iterationsWithoutCollisions > 1000) {
                jobDone = true;
            }
        }
        System.out.println(particles.size());
    }

    private List<Particle> getParticlesWithoutCollisions(List<Particle> particles) {
        Map<List<Long>, List<Particle>> mapping = new HashMap<>();
        List<Particle> particlesCopy = new ArrayList<>(particles);

        for (Particle p :
                particlesCopy) {
            if (mapping.containsKey(p.getCoordinatesList())) {
                mapping.get(p.getCoordinatesList()).add(p);
            } else {
                List<Particle> list = new ArrayList<>();
                list.add(p);
                mapping.put(p.getCoordinatesList(), list);
            }
        }

        for (Map.Entry<List<Long>, List<Particle>> pair :
                mapping.entrySet()) {
            if (pair.getValue().size() > 1) {
                particlesCopy.removeAll(pair.getValue());
            }
        }

        return particlesCopy;
    }


    private List<Particle> parseParticles(String[] input) {
        List<Particle> result = new ArrayList<>();
        for (String s :
                input) {
            result.add(parseParticle(s));
        }

        return result;
    }

    private Particle parseParticle(String s) {
        String[] parts = s.split("[pva=<> ,]+");
        long[] coordinates = new long[3];
        long[] velocity = new long[3];
        long[] acceleration = new long[3];

        coordinates[0] = Integer.parseInt(parts[1]);
        coordinates[1] = Integer.parseInt(parts[2]);
        coordinates[2] = Integer.parseInt(parts[3]);

        velocity[0] = Integer.parseInt(parts[4]);
        velocity[1] = Integer.parseInt(parts[5]);
        velocity[2] = Integer.parseInt(parts[6]);

        acceleration[0] = Integer.parseInt(parts[7]);
        acceleration[1] = Integer.parseInt(parts[8]);
        acceleration[2] = Integer.parseInt(parts[9]);

        return new Particle(coordinates, velocity, acceleration);

    }


    private class Particle {
        private long[] coordinates;
        private long[] velocity;
        private long[] acceleration;

        private int averageZeroDistance;
        private List<Long> allDistances;

        public List<Long> getCoordinatesList() {
            List<Long> result = new ArrayList<>();
            result.add(coordinates[0]);
            result.add(coordinates[1]);
            result.add(coordinates[2]);
            return result;
        }

        public Particle(long[] coordinates, long[] velocity, long[] acceleration) {
            this.coordinates = coordinates;
            this.velocity = velocity;
            this.acceleration = acceleration;
            averageZeroDistance = 0;
            allDistances = new ArrayList<>();
        }

        public void iteration() {
            velocity[0] += acceleration[0];
            velocity[1] += acceleration[1];
            velocity[2] += acceleration[2];

            coordinates[0] += velocity[0];
            coordinates[1] += velocity[1];
            coordinates[2] += velocity[2];

            allDistances.add(Math.abs(coordinates[0]) + Math.abs(coordinates[1]) + Math.abs(coordinates[2]));
        }

        public double getAverageZeroDistance() {
            long sum = 0;
            for (Long i :
                    allDistances) {
                sum += i;
            }
            return sum / allDistances.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Particle particle = (Particle) o;

            if (averageZeroDistance != particle.averageZeroDistance) return false;
            if (!Arrays.equals(coordinates, particle.coordinates)) return false;
            if (!Arrays.equals(velocity, particle.velocity)) return false;
            if (!Arrays.equals(acceleration, particle.acceleration)) return false;
            return allDistances != null ? allDistances.equals(particle.allDistances) : particle.allDistances == null;
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(coordinates);
            result = 31 * result + Arrays.hashCode(velocity);
            result = 31 * result + Arrays.hashCode(acceleration);
            result = 31 * result + averageZeroDistance;
            result = 31 * result + (allDistances != null ? allDistances.hashCode() : 0);
            return result;
        }
    }
}
