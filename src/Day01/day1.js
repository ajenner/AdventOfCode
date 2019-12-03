const fs = require('fs')

const contents = fs.readFileSync('./src/Data/d1.txt', 'utf8').trim()
const lines = contents.split('\n')
const masses = lines.map(Number)

const massToFuelRequirement = mass => Math.floor(mass / 3) - 2
const fuelRequirements = masses.map(massToFuelRequirement)
const totalFuelRequirements = fuelRequirements.map(input => {
  let fuelTotal = input
  let currentIterationAmount = massToFuelRequirement(input)
  while(currentIterationAmount > 0) {
    fuelTotal += currentIterationAmount
    currentIterationAmount = massToFuelRequirement(currentIterationAmount)
  }
  return fuelTotal
})

const total1 = fuelRequirements.reduce((a, b) => a + b, 0)
console.log('Day1 Part1: ${total1}');
const total2 = totalFuelRequirements.reduce((a, b) => a + b, 0)
console.log('Day2 Part2: ${total2}');
