const fs = require('fs')

const contents = fs.readFileSync('./src/Data/d2.txt', 'utf8').trim()
const inputs = contents.split(',').map(Number)
var memory = Object.assign({}, inputs)

const question1 = () => {
  let programCounter = 0
  memory[1] = 12
  memory[2] = 2
  calculate(memory, programCounter)
  console.log(memory[0])
}

const question2 = target => {
  for (let noun = 0; noun < 100; noun++) {
    for (let verb = 0; verb < 100; verb++) {
      let programCounter = 0
      const temp = Object.assign({}, inputs)
      temp[1] = noun
      temp[2] = verb
      calculate(temp, programCounter)
      if (temp[0] === target) {
        console.log({noun, verb, result : (100 * noun) + verb})
      }
    }
  }
}

const calculate = (memory, programCounter) => {
  while(memory[programCounter] != 99) {
    switch (memory[programCounter]) {
      case 1:
        memory[memory[programCounter + 3]] = memory[memory[programCounter + 1]] + memory[memory[programCounter + 2]]
        break;
      case 2:
        memory[memory[programCounter + 3]] = memory[memory[programCounter + 1]] * memory[memory[programCounter + 2]]
        break;
      default:
        console.log('Why have you done this?')
    }
    programCounter += 4
  }
}

question1()
question2(19690720)
